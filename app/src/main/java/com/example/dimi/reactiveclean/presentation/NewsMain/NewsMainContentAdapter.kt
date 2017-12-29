package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class NewsMainContentAdapter : ListDelegationAdapter<MutableList<BaseItemDisplayable>>() {

    private lateinit var disposable: Disposable

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(NewsMainContentDisplayableAdapter())
        delegatesManager.addDelegate(LoadingDisplayableAdapter())
        delegatesManager.addDelegate(ErrorDisplayableAdapter())
    }

    fun updateItems(newList: List<BaseItemDisplayable>) {
        if (this::disposable.isInitialized) disposable.dispose()

        disposable = Single.fromCallable { calculateDiff(newList) }
                .doOnSuccess { updateAdapterData(newList) }
                .subscribe(this::dispatchResult)

    }

    fun unsubscribe() {
        disposable.dispose()
    }

    private fun calculateDiff(newList: List<BaseItemDisplayable>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(DiffUtilContent(items, newList))
    }

    private fun updateAdapterData(newList: List<BaseItemDisplayable>) {
        items.clear()
        items.addAll(newList)
    }

    private fun dispatchResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this@NewsMainContentAdapter)
    }
}

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

//    private fun calculateDiffUtil(pair: Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>,
//                                  list: List<BaseItemDisplayable>): Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult> {
//        val callback = DiffUtilContent(pair.first, list)
//        val result = DiffUtil.calculateDiff(callback, true)
//        return Pair(list, result)