package com.example.dimi.reactiveclean.presentation.main.adapters

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.utils.DiffUtilSearch
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class SearchAdapter(
        private val schedulers: SchedulersProvider,
        callback: (SearchDisplayable.Search) -> Unit
) : ListDelegationAdapter<MutableList<SearchDisplayable>>() {

    private var disposable: Disposable? = null

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(SearchModelDisplayableAdapter(callback))

    }

    fun setNewData(data: List<SearchDisplayable>) {
        disposable?.dispose()
        disposable = Single.fromCallable { DiffUtil.calculateDiff(DiffUtilSearch(items, data)) }
                .subscribeOn(schedulers.computation())
                .observeOn(schedulers.ui())
                .subscribe({
                    items.apply { clear() }.addAll(data)
                    it.dispatchUpdatesTo(this)
                }, { throw Exception(it.message) }
                )
    }

    fun disposeSubscription() {
        disposable?.dispose()
    }
}