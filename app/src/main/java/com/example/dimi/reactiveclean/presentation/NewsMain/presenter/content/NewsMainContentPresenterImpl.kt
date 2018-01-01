package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.models.content.Item
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.content.*
import com.example.dimi.reactiveclean.utils.Paginator
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsMainContentPresenterImpl
@Inject constructor(private val interractor: NewsMainContentInterractor,
                    private val mapper: NewsMainContentDomainMapper) : NewsMainContentPresenter {

    private var contentLiveData: MutableLiveData<ContentDisplayable> = MutableLiveData()

    private var lastPosition: Int = 0

    private var compositeDisposable = CompositeDisposable()

    private val paginator = Paginator(
            initRequest = this::initRequest,
            databaseStream = this::getDatabaseStream,
            pageRequest = this::getContentPage,
            viewController = object : Paginator.ViewController<Item.Content> {

                /**
                 * Appears like an additional view on the top of recycler with animation
                 */
                override fun showEmptyProgress(show: Boolean) {
                    var a = 2
                    a++
                }

                /**
                 * Appears like a toast, when shown old data from DB, Request was failed
                 */
                override fun showDatabaseMessage(error: Throwable?) {
                    var a = 2
                    a++
                }

                /**
                 * Appears like an additional view on the top of recycler, means that there is no value
                 */
                override fun showEmptyView(show: Boolean) {
                    var a = 2
                    a++
                }

                override fun showData(show: Boolean, data: List<Item.Content>) {
                    val newList: MutableList<Item> = mutableListOf()
                    newList.addAll(data)
                    contentLiveData.postValue(ContentDisplayable(content = newList, state = ContentState.DATA))
                }


                override fun showErrorMessage(error: Throwable, data: List<Item.Content>) {
                    contentLiveData.postValue(ContentDisplayable(data, state = ContentState.ERROR))
                }

                override fun showRefreshProgress(show: Boolean) {

                }

                override fun showPageProgress(data: List<Item.Content>) {
                    contentLiveData.postValue(ContentDisplayable(data, state = ContentState.PROGRESS))
                }
            })

    override fun getData(): LiveData<ContentDisplayable> = contentLiveData

    init {
        refreshContent()
    }


    override fun disposeSubscriptions() {
        compositeDisposable.clear()
        paginator.release()
    }

    override fun refreshContent() {
        paginator.refresh()
    }

    override fun loadNextContentPage() {
        paginator.loadNewPage()
    }

    override fun setVisibleItem(position: Int) {
        lastPosition = position
    }

    override fun getVisibleItem(): Int = lastPosition

    override fun subscribeSearchText(text: Observable<String>) {
        interractor.searchContent(text)
                .subscribe({
                    var a = 3
                    a++
                }, {
                    var a = 3
                    a++
                })
                .addTo(compositeDisposable)

    }

    override fun disposeRxBinding() {
        compositeDisposable.clear()
    }

    private fun getContentPage(page: Int): Completable {
        return interractor.loadNextContentPage(page)
    }

    private fun initRequest(): Completable {
        return interractor.loadNews()
    }

    private fun getDatabaseStream(): Flowable<List<Item.Content>> {
        return interractor.getContentStream().map(mapper)
    }
}
