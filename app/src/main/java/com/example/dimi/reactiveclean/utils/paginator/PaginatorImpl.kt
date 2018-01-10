package com.example.dimi.reactiveclean.utils.paginator

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.models.content.ContentState
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PaginatorImpl<T>
@Inject constructor() : Paginator<T> {

    private lateinit var pageRequest: (Int) -> Single<List<T>>

    private val FIRST_PAGE = 1


    private var paginatorResult: PaginatorResult<T>? = null
        set(value) {
            field = value
            dataLiveData.postValue(value)
            value?.let {
                it.showErrorMessage?.let {
                    errorMessageLiveData.postValue("ERROR CONNECTION")
                }
            }
        }

    private val dataLiveData: MutableLiveData<PaginatorResult<T>> = MutableLiveData()
    private val errorMessageLiveData = SingleEventLiveData<String>()

    private var currentData: MutableList<T> = mutableListOf()
    private var currentState: PaginatorImpl.State<T> = EMPTY()
    private var currentPage = 0
    private var disposable: Disposable? = null

    override fun disposeSubscriptions() {
       currentState.release()
    }

    override fun getData(): LiveData<PaginatorResult<T>> = dataLiveData

    override fun setCallback(callback: (page: Int) -> Single<List<T>>) {
        pageRequest = callback
    }

    override fun refresh() {
        currentState.refresh()
    }

    override fun loadNextPage() {
        currentState.loadNewPage()
    }

    override fun getSingleEvent(): LiveData<String> = errorMessageLiveData

    private fun loadPage(page: Int) {
        disposable?.dispose()
        disposable = pageRequest.invoke(page)
                .subscribe(
                        { currentState.newData(it) },
                        { currentState.fail(it) }
                )
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
            paginatorResult = PaginatorResult(showEmptyProgress = true, showEmptyView = false)
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_PROGRESS : State<T> {

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentPage = FIRST_PAGE
                val paginatorData = PaginatorData(
                        content = data, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.DATA
                )
                paginatorResult = PaginatorResult(showEmptyProgress = false,
                        showEmptyView = false, paginatorData = paginatorData)
            } else {
                currentState = EMPTY_DATA()
                paginatorResult = PaginatorResult(showEmptyProgress = false, showEmptyView = true)
            }
        }

        override fun fail(error: Throwable) {
            currentState = EMPTY_ERROR()
            paginatorResult = PaginatorResult(showEmptyProgress = false, showErrorMessage = true, paginatorData = PaginatorData(
                    content = currentData, state = ContentState.DATA, recyclerUpdate = RecyclerUpdate.DIFF_UTIL
            ))
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_ERROR : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            paginatorResult = PaginatorResult(showEmptyProgress = true)
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_DATA : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            paginatorResult = PaginatorResult(showEmptyProgress = true, showEmptyView = false)
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class DATA : State<T> {

        override fun refresh() {
            currentState = REFRESH()
            paginatorResult = PaginatorResult(showRefreshProgress = true)
            loadPage(FIRST_PAGE)
        }

        override fun loadNewPage() {
            currentState = PAGE_PROGRESS()
            val paginatorData = PaginatorData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.PROGRESS
            )
            paginatorResult = PaginatorResult(paginatorData = paginatorData)
            loadPage(currentPage + 1)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class REFRESH : State<T> {

        override fun newData(data: List<T>) {
            paginatorResult = if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentPage = FIRST_PAGE
                val paginatorData = PaginatorData(
                        content = data, recyclerUpdate = RecyclerUpdate.NOTIFY, state = ContentState.DATA
                )
                PaginatorResult(paginatorData = paginatorData, showRefreshProgress = false)
            } else {
                currentState = EMPTY_DATA()
                currentData.clear()
                val paginatorData = PaginatorData(
                        content = currentData, recyclerUpdate = RecyclerUpdate.NOTIFY, state = ContentState.DATA
                )
                PaginatorResult(
                        showEmptyView = true, showRefreshProgress = false, paginatorData = paginatorData)
            }
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            val paginatorData = PaginatorData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.ERROR
            )
            paginatorResult = PaginatorResult(paginatorData = paginatorData, showRefreshProgress = false)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
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
            val paginatorData = PaginatorData(
                    content = data, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.DATA
            )
            paginatorResult = PaginatorResult(paginatorData = paginatorData)
        }

        override fun refresh() {
            currentState = REFRESH()
            paginatorResult = PaginatorResult(showRefreshProgress = true)
            loadPage(FIRST_PAGE)
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            val paginatorData = PaginatorData(
                    content = currentData, recyclerUpdate = RecyclerUpdate.DIFF_UTIL, state = ContentState.ERROR
            )
            paginatorResult = PaginatorResult(paginatorData = paginatorData)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class ALL_DATA : State<T> {

        override fun refresh() {
            currentState = REFRESH()
            paginatorResult = PaginatorResult(showRefreshProgress = true)
            loadPage(FIRST_PAGE)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class RELEASED : State<T>
}