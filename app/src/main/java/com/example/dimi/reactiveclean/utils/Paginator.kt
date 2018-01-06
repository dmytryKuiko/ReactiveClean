//package com.example.dimi.reactiveclean.utils
//
//import com.example.dimi.reactiveclean.extensions.addTo
//import com.example.dimi.reactiveclean.models.RecyclerUpdate
//import io.reactivex.Completable
//import io.reactivex.Flowable
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.disposables.Disposable
//
//class Paginator<T>(
//        private val initRequest: () -> Completable,
//        private val databaseStream: () -> Flowable<List<T>>,
//        private val pageRequest: (Int) -> Completable,
//        private val viewController: ViewController<T>
//) {
//
//    interface ViewController<T> {
//        fun showEmptyProgress(show: Boolean)
//        fun showDatabaseMessage(error: Throwable? = null)
//        fun showEmptyView(show: Boolean)
//        fun showData(show: Boolean, data: List<T> = emptyList(), recyclerUpdate: RecyclerUpdate = RecyclerUpdate.DIFF_UTIL)
//        fun showErrorMessage(error: Throwable, data: List<T> = emptyList())
//        fun showRefreshProgress(show: Boolean)
//        fun showPageProgress(data: List<T> = emptyList())
//    }
//
//    private val FIRST_PAGE = 1
//
//    private var currentState: State<T> = EMPTY()
//    private var currentPage = 0
//    private val currentData = mutableListOf<T>()
//    private var requestDisposable: Disposable? = null
//    private var databaseDisposable: CompositeDisposable = CompositeDisposable()
//
//    fun refresh() {
//        currentState.refresh()
//    }
//
//    fun loadNewPage() {
//        currentState.loadNewPage()
//    }
//
//    fun disposeSubscriptions() {
//        currentState.release()
//    }
//
//    private fun loadPage(page: Int) {
//        requestDisposable?.dispose()
//        requestDisposable = pageRequest.invoke(page)
//                .subscribe(
//                        { },
//                        { currentState.fail(it) }
//                )
//    }
//
//    private fun makeInitRequest() {
//        requestDisposable?.dispose()
//        requestDisposable = initRequest.invoke()
//                .doAfterTerminate(this::checkDataBaseDisposable)
//                .subscribe(
//                        { },
//                        { currentState.fail(it) }
//                )
//    }
//
//    private fun checkDataBaseDisposable() {
//        if (databaseDisposable.size() == 0) subscribeToDB()
//    }
//
//    private fun subscribeToDB() {
//        databaseStream.invoke()
//                .subscribe(
//                        { currentState.newData(it) },
//                        { currentState.fail(it) }
//                )
//                .addTo(databaseDisposable)
//    }
//
//    private interface State<T> {
//
//        fun refresh() {}
//        fun loadNewPage() {}
//        fun release() {}
//        fun newData(data: List<T>) {}
//        fun fail(error: Throwable) {}
//    }
//
//    private inner class EMPTY : State<T> {
//
//        override fun refresh() {
//            currentState = EMPTY_PROGRESS()
//            viewController.showEmptyView(false)
//            viewController.showEmptyProgress(true)
//            makeInitRequest()
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class EMPTY_PROGRESS : State<T> {
//
//        override fun newData(data: List<T>) {
//            if (data.isNotEmpty()) {
//                currentState = DATA()
//                currentData.clear()
//                currentData.addAll(data)
//                currentPage = FIRST_PAGE
//                viewController.showEmptyProgress(false)
//                viewController.showData(true, currentData)
//            } else {
//                currentState = EMPTY_DATA()
//                viewController.showEmptyProgress(false)
//                viewController.showEmptyView(true)
//            }
//        }
//
//        override fun fail(error: Throwable) {
//            currentState = DATABASE()
//
//            //TODO()
//            viewController.showEmptyProgress(false)
//            viewController.showDatabaseMessage(error)
//            //------------------------------------------------
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class DATABASE : State<T> {
//
//        override fun refresh() {
//            currentState = EMPTY_PROGRESS()
//            viewController.showEmptyView(false)
//            viewController.showEmptyProgress(true)
//            makeInitRequest()
//        }
//
//        //TODO()------------------------------------------------
//        override fun newData(data: List<T>) {
//            currentState = EMPTY()
//            if (data.isNotEmpty()) {
//                currentData.clear()
//                currentData.addAll(data)
//                viewController.showData(true, currentData)
//            } else {
//                viewController.showEmptyView(true)
//            }
//        }
//        //------------------------------------------------
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class EMPTY_DATA : State<T> {
//
//        override fun refresh() {
//            currentState = EMPTY_PROGRESS()
//            viewController.showEmptyView(false)
//            viewController.showEmptyProgress(true)
//            makeInitRequest()
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class DATA : State<T> {
//
//        override fun refresh() {
//            currentState = REFRESH()
//            viewController.showRefreshProgress(true)
//            makeInitRequest()
//        }
//
//        override fun loadNewPage() {
//            currentState = PAGE_PROGRESS()
//            viewController.showPageProgress(currentData)
//            loadPage(currentPage + 1)
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class REFRESH : State<T> {
//
//        override fun newData(data: List<T>) {
//            if (data.isNotEmpty()) {
//                currentState = DATA()
//                currentData.clear()
//                currentData.addAll(data)
//                currentPage = FIRST_PAGE
//                viewController.showRefreshProgress(false)
//                viewController.showData(true, currentData, recyclerUpdate = RecyclerUpdate.NOTIFY_RANGES)
//            } else {
//                currentState = EMPTY_DATA()
//                currentData.clear()
//                viewController.showData(false)
//                viewController.showRefreshProgress(false)
//                viewController.showEmptyView(true)
//            }
//        }
//
//        override fun fail(error: Throwable) {
//            currentState = DATA()
//            viewController.showRefreshProgress(false)
//            viewController.showErrorMessage(error, currentData)
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class PAGE_PROGRESS : State<T> {
//
//        override fun newData(data: List<T>) {
//            if (data.isNotEmpty()) {
//                currentState = DATA()
//                currentData.clear()
//                currentData.addAll(data)
//                currentPage++
//                viewController.showData(true, currentData)
//            } else {
//                currentState = ALL_DATA()
//                viewController.showData(true, currentData)
//            }
//        }
//
//        override fun refresh() {
//            currentState = REFRESH()
//            viewController.showRefreshProgress(true)
//            makeInitRequest()
//        }
//
//        override fun fail(error: Throwable) {
//            currentState = DATA()
//            viewController.showErrorMessage(error, currentData)
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class ALL_DATA : State<T> {
//
//        override fun refresh() {
//            currentState = REFRESH()
//            viewController.showRefreshProgress(true)
//            makeInitRequest()
//        }
//
//        override fun release() {
//            currentState = RELEASED()
//            requestDisposable?.dispose()
//            databaseDisposable.clear()
//        }
//    }
//
//    private inner class RELEASED : State<T>
//}