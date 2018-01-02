package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.content.Item
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.ContentState
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class NewsMainContentAdapter(
        private val loadNextPage: () -> Unit,
        private val scrollTo: () -> Unit,
        private val schedulers: SchedulersProvider) : ListDelegationAdapter<MutableList<Item>>() {

    private val deltaPositionLoading = 5

    private var disposable: Disposable? = null

    private var needsScrolling = true

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(NewsMainContentDisplayableAdapter())
                .addDelegate(ProgressAdapter())
                .addDelegate(ErrorAdapter(loadNextPage))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int, payloads: MutableList<Any?>?) {
        super.onBindViewHolder(holder, position, payloads)
        if (position == items.size - deltaPositionLoading && !isError()) loadNextPage.invoke()
    }

    fun setNewData(model: ContentDisplayable) {
        disposable?.dispose()
        val newList: MutableList<Item> = mutableListOf()
        newList.addAll(model.content)
        when (model.state) {
            ContentState.DATA -> {}
            ContentState.PROGRESS -> newList.add(Item.Progress())
            ContentState.ERROR -> newList.add(Item.Error())
        }
        when (model.recyclerUpdate) {
            RecyclerUpdate.DIFF_UTIL -> disposable = notifyDiffUtil(newList)
            RecyclerUpdate.NOTIFY_RANGES -> notifyRanges(newList)
        }

    }

    fun disposeSubscription() {
        disposable?.dispose()
    }

    private fun isError() = items.last() is Item.Error

    private fun notifyDiffUtil(newList: List<Item>): Disposable {
        return Single.fromCallable { DiffUtil.calculateDiff(DiffUtilContent(items, newList)) }
                .compose(::composeSchedulers)
                .subscribe({
                    items.apply { clear() }.addAll(newList)
                    it.dispatchUpdatesTo(this@NewsMainContentAdapter)
                    checkAndScroll()
                },
                        { throw Exception(it.message) }
                )
    }

    private fun notifyRanges(newList: List<Item>) {
        val oldSize = items.size
        val newSize = newList.size
        items.apply { clear() }.addAll(newList)
        notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, newSize)
    }

    private fun checkAndScroll() {
        if (needsScrolling) {
            scrollTo.invoke()
            needsScrolling = false
        }
    }

    private fun composeSchedulers(single: Single<DiffUtil.DiffResult>): Single<DiffUtil.DiffResult> =
            single.subscribeOn(schedulers.computation())
                    .observeOn(schedulers.ui())
}