package com.example.dimi.reactiveclean.utils.paginator

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.models.content.ContentState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PaginatorDBImpl<T>
@Inject constructor() : PaginatorDB<T> {

    private lateinit var initRequest: () -> Completable
    private lateinit var databaseStream: () -> Flowable<List<T>>
    private lateinit var pageRequest: (Int) -> Completable

    private val FIRST_PAGE = 1

    private var paginatorModelResult: PaginatorModelResult<T>? = null
        set(value) {
            field = value
            dataLiveData.postValue(value)
            value?.let {
                it.showErrorMessage?.let {
                    databaseMessageLiveData.postValue("DATABASE")
                }
            }
        }

    private val dataLiveData: MutableLiveData<PaginatorModelResult<T>> = MutableLiveData()
    private val databaseMessageLiveData = SingleEventLiveData<String>()

    private var currentData: MutableList<T> = mutableListOf()
    private var currentState: State<T> = EMPTY()
    private var currentPage = 0
    private var requestDisposable: Disposable? = null
    private var databaseDisposable: CompositeDisposable = CompositeDisposable()

    override fun getData(): LiveData<PaginatorModelResult<T>> = dataLiveData

    override fun getSingleEvent(): LiveData<String> = databaseMessageLiveData

    override fun disposeSubscriptions() {
        currentState.release()
    }

    override fun setCallbacks(init: () -> Completable, database: () -> Flowable<List<T>>, nextData: (Int) -> Completable) {
        initRequest = init
        databaseStream = database
        pageRequest = nextData
    }

    override fun refresh() {
        currentState.refresh()
    }

    override fun loadNewPage() {
        currentState.loadNewPage()
    }

    private fun loadPage(page: Int) {
        requestDisposable?.dispose()
        requestDisposable = pageRequest.invoke(page)
                .subscribe(
                        { },
                        { currentState.fail(it) }
                )
    }

    private fun makeInitRequest() {
        requestDisposable?.dispose()
        requestDisposable = initRequest.invoke()
                .doAfterTerminate(this::checkDataBaseDisposable)
                .subscribe(
                        { },
                        { currentState.fail(it) }
                )
    }

    private fun checkDataBaseDisposable() {
        if (databaseDisposable.size() == 0) subscribeToDB()
    }

    private fun subscribeToDB() {
        databaseStream.invoke()
                .subscribe(
                        { currentState.newData(it) },
                        { currentState.fail(it) }
                )
                .addTo(databaseDisposable)
    }

    private interface State<T> {
        fun refresh() {}
        fun loadNewPage() {}
        fun release() {}
        fun newData(data: List<T>) {}
        fun fail(error: Throwable) {}
    }

    private inner class EMPTY : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            paginatorModelResult = PaginatorModelResult(showEmptyProgress = true, showEmptyView = false)
            makeInitRequest()
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class EMPTY_PROGRESS : State<T> {

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentPage = FIRST_PAGE
                val paginatorData = PaginatorModelData(
                        content = data, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.DATA
                )
                paginatorModelResult = PaginatorModelResult(showEmptyProgress = false,
                        showEmptyView = false, paginatorModelData = paginatorData)
            } else {
                currentState = EMPTY_DATA()
                paginatorModelResult = PaginatorModelResult(showEmptyProgress = false, showEmptyView = true)
            }
        }

        override fun fail(error: Throwable) {
            currentState = DATABASE()
            paginatorModelResult = PaginatorModelResult(showEmptyProgress = false, showErrorMessage = true, paginatorModelData = PaginatorModelData(
                    content = currentData, state = ContentState.DATA, recyclerUpdate = RecyclerUpdate.DIFF_UTIL
            ))
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class DATABASE : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            paginatorModelResult = PaginatorModelResult(showEmptyProgress = true, showEmptyView = false)
            makeInitRequest()
        }

        //TODO()------------------------------------------------
        override fun newData(data: List<T>) {
            currentState = EMPTY()
            paginatorModelResult = if (data.isNotEmpty()) {
                currentData.clear()
                currentData.addAll(data)
                val paginatorData = PaginatorModelData(
                        content = data, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.DATA
                )
                PaginatorModelResult(paginatorModelData = paginatorData)
            } else {
                PaginatorModelResult(showEmptyView = true)
            }
        }
        //------------------------------------------------

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class EMPTY_DATA : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            paginatorModelResult = PaginatorModelResult(showEmptyProgress = true, showEmptyView = false)
            makeInitRequest()
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class DATA : State<T> {

        override fun refresh() {
            currentState = REFRESH()
            paginatorModelResult = PaginatorModelResult(showRefreshProgress = true)
            makeInitRequest()
        }

        override fun loadNewPage() {
            currentState = PAGE_PROGRESS()
            val paginatorData = PaginatorModelData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.PROGRESS
            )
            paginatorModelResult = PaginatorModelResult(paginatorModelData = paginatorData)
            loadPage(currentPage + 1)
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class REFRESH : State<T> {

        override fun newData(data: List<T>) {
            paginatorModelResult = if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentPage = FIRST_PAGE
                val paginatorData = PaginatorModelData(
                        content = data, recyclerUpdate = RecyclerUpdate.NOTIFY, state = ContentState.DATA
                )
                PaginatorModelResult(paginatorModelData = paginatorData, showRefreshProgress = false)
            } else {
                currentState = EMPTY_DATA()
                currentData.clear()
                val paginatorData = PaginatorModelData(
                        content = currentData, recyclerUpdate = RecyclerUpdate.NOTIFY, state = ContentState.DATA
                )
                PaginatorModelResult(
                        showEmptyView = true, showRefreshProgress = false, paginatorModelData = paginatorData)
            }
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            val paginatorData = PaginatorModelData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.ERROR
            )
            paginatorModelResult = PaginatorModelResult(paginatorModelData = paginatorData, showRefreshProgress = false)
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class PAGE_PROGRESS : State<T> {

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentPage++
            } else {
                currentState = ALL_DATA()
            }
            val paginatorData = PaginatorModelData(
                    content = data, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.DATA
            )
            paginatorModelResult = PaginatorModelResult(paginatorModelData = paginatorData)
        }

        override fun refresh() {
            currentState = REFRESH()
            paginatorModelResult = PaginatorModelResult(showRefreshProgress = true)
            makeInitRequest()
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            val paginatorData = PaginatorModelData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.ERROR
            )
            paginatorModelResult = PaginatorModelResult(paginatorModelData = paginatorData)
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class ALL_DATA : State<T> {

        override fun refresh() {
            currentState = REFRESH()
            paginatorModelResult = PaginatorModelResult(showRefreshProgress = true)
            makeInitRequest()
        }

        override fun release() {
            currentState = RELEASED()
            requestDisposable?.dispose()
            databaseDisposable.clear()
        }
    }

    private inner class RELEASED : State<T>
}

//private var paginator: PaginatorDB<ContentDisplayable> = PaginatorChangedContent()
//    initRequest = this::initRequest,
//    databaseStream = this::getDatabaseStream, pageRequest = this::getContentPage

//    private val paginator = Paginator(
//            initRequest = this::initRequest,
//            databaseStream = this::getDatabaseStream,
//            pageRequest = this::getContentPage,
//            viewController = object : Paginator.ViewController<ContentDisplayable.Content> {
//
//                /**
//                 * Appears like an additional view on the top of recycler with animation
//                 * almost the same as showRefreshProgress
//                 */
//                override fun showEmptyProgress(show: Boolean) {
//                    var a = 2
//                    a++
//                }
//
//                /**
//                 * Appears like a toast, when shown old data from DB, Request was failed
//                 */
//                override fun showErrorMessage(error: Throwable?) {
//                    var a = 2
//                    a++
//                }
//
//                /**
//                 * Appears like an additional view on the top of recycler, means that there is no value
//                 */
//                override fun showEmptyView(show: Boolean) {
//                    var a = 2
//                    a++
//                }
//
//                override fun showData(show: Boolean, data: List<ContentDisplayable.Content>, recyclerUpdate: RecyclerUpdate) {
//                    val newList: MutableList<ContentDisplayable> = mutableListOf()
//                    newList.addAll(data)
//                    contentLiveData.postValue(
//                            ContentDisplayable(content = newList, state = ContentState.DATA,
//                                    recyclerUpdate = recyclerUpdate)
//                    )
//                }
//
//
//                override fun showErrorMessage(error: Throwable, data: List<ContentDisplayable.Content>) {
//                    contentLiveData.postValue(
//                            ContentDisplayable(data, state = ContentState.ERROR,
//                                    recyclerUpdate = RecyclerUpdate.DIFF_UTIL)
//                    )
//                }
//
//                /**
//                 * Appears like an addtional view on the top of recycler, means global refreshing
//                 * almost the same as showEmptyProgress
//                 */
//                override fun showRefreshProgress(show: Boolean) {
//
//                }
//
//                override fun showPageProgress(data: List<ContentDisplayable.Content>) {
//                    contentLiveData.postValue(
//                            ContentDisplayable(data, state = ContentState.PROGRESS,
//                                    recyclerUpdate = RecyclerUpdate.DIFF_UTIL)
//                    )
//                }
//            })
