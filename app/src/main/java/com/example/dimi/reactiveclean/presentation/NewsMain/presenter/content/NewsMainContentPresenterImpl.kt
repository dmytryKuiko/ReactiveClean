package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.models.content.*
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NewsMainContentPresenterImpl
@Inject constructor(private val interractor: NewsMainContentInterractor,
                    private val mapper: NewsMainContentDomainMapper,
                    private var pages: ContentPages) : NewsMainContentPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val contentLiveData: MutableLiveData<List<ContentDisplayable>> = MutableLiveData()

    private val showErrorLiveData = SingleEventLiveData<Unit>()

    private var pagesPublishRelay: PublishRelay<ContentPages> = PublishRelay.create()

    private lateinit var disposableSearch: Disposable

    init {
        interractor.loadNews()
                .doAfterTerminate(this::subscribeToDb)
                .subscribe({
                    pages = it
                    pagesPublishRelay.accept(it)
                }, { t ->
                    var a = 2
                    a++
                }).addTo(compositeDisposable)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun getData(): LiveData<List<ContentDisplayable>> = contentLiveData

    override fun getError(): LiveData<Unit> = showErrorLiveData

    override fun listenRecyclerScrollAndItems(lastVisibleAndAllItems: Observable<ContentRecyclerData>) {
        interractor.loadMoreContent(lastVisibleAndAllItems, pagesPublishRelay)
                .subscribe({
                    pages = it
                    pagesPublishRelay.accept(it)
                }, (this::onErrorContentStream))
                .addTo(compositeDisposable)
    }

    private fun subscribeToDb() {
        interractor.getContentStream()
                .map(mapper)
                .subscribe(contentLiveData::postValue, this::onErrorContentStream)
                .addTo(compositeDisposable)
    }

    private fun onErrorContentStream(throwable: Throwable) = showErrorLiveData.call()
//
//    private fun addLoadingAndCalculateDiff(pair: Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>,
//                                           next: List<BaseItemDisplayable>): Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult> {
//        val newList: MutableList<BaseItemDisplayable> = mutableListOf()
//        newList.addAll(next)
//
//        if (pages.currentPage < pages.pages) {
//            newList.add(LoadingDisplayable())
//        }
//        return calculateDiffUtil(pair, newList)
//    }

//    private fun showErrorViewHolder(throwable: Throwable) {
//        pairLiveData.value?.let {
//            val newResults: MutableList<BaseItemDisplayable> = mutableListOf()
//            with(newResults) {
//                addAll(it.first.subList(0, it.first.size - 1))
//                if (it.first.last() !is LoadingDisplayable) {
//                    add(it.first.last())
//                }
//                add(ErrorDisplayable(true))
//            }
//            val newPair = calculateDiffUtil(it, newResults)
//            pairLiveData.postValue(newPair)
//        }
//    }
}
