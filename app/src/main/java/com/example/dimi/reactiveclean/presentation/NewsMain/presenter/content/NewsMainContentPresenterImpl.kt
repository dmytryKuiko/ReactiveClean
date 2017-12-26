package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.models.content.ErrorDisplayable
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NewsMainContentPresenterImpl
@Inject constructor(private val interractor: NewsMainContentInterractor,
                    private val mapper: NewsMainContentDomainMapper) : NewsMainContentPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val sectionDisplayableLiveData: MutableLiveData<Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>> = MutableLiveData()

    private val showErrorLiveData = SingleEventLiveData<Unit>()

    private lateinit var disposableSearch: Disposable

    init {
        val temp = interractor.loadNews()
                .doAfterTerminate(this::subscribeToDb)
                .subscribe({ }, { t ->
                    var a = 2
                    a++
                })

        compositeDisposable.add(temp)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun getData(): LiveData<Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>> = sectionDisplayableLiveData

    override fun getError(): LiveData<Unit> = showErrorLiveData

    override fun listenRecyclerScrollAndItems(lastVisibleAndAllItems: Observable<Pair<Int, Int>>) {
        val disposableScrolling = interractor.loadMoreContent(lastVisibleAndAllItems)
                .subscribe({
                    var a = 3
                    a++
                }, (this::showErrorViewHolder))

        compositeDisposable.add(disposableScrolling)
    }

    private fun subscribeToDb() {
        val disposable = interractor.getContentStream()
                .map(mapper)
                .scan(createInitialPair(), this::calculateDiffUtilScan)
                .skip(1)
                .subscribe(this::eventReceived, this::errorReceived)

        compositeDisposable.add(disposable)
    }


    private fun createInitialPair(): Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult> {
        val initialList = listOf(LoadingDisplayable(true))
        val initialCallback = DiffUtilContent(initialList, initialList)
        return Pair(listOf(LoadingDisplayable(true)), DiffUtil.calculateDiff(initialCallback))
    }

    private fun calculateDiffUtilScan(pair: Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>,
                                      next: List<BaseItemDisplayable>): Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult> {
        val callback = DiffUtilContent(pair.first, next)
        val result = DiffUtil.calculateDiff(callback, true)
        return Pair(next, result)
    }

    //TODO CREATE ERROR HOLDER
    private fun showErrorViewHolder(throwable: Throwable) {
        sectionDisplayableLiveData.value?.let {
            val newResults: MutableList<BaseItemDisplayable> = mutableListOf()
            with(newResults) {
                addAll(it.first.subList(0, it.first.size - 1))
                if(it.first.last() !is LoadingDisplayable) {
                    add(it.first.last())
                }
                add(ErrorDisplayable(true))
            }
            val newPair = calculateDiffUtilScan(it, newResults)
            sectionDisplayableLiveData.postValue(newPair)
        }
    }

    private fun eventReceived(list: Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>) =
            sectionDisplayableLiveData.postValue(list)

    private fun errorReceived(throwable: Throwable) = showErrorLiveData.call()
}
