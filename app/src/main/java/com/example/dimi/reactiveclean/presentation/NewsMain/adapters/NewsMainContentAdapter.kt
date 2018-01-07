package com.example.dimi.reactiveclean.presentation.NewsMain.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.ContentState
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import com.example.dimi.reactiveclean.utils.paginator.PaginatorData
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import timber.log.Timber

class NewsMainContentAdapter(
        private val loadNextPage: () -> Unit,
        private val scrollTo: () -> Unit,
        val openCurrentContent: (ContentDisplayable.Content) -> Unit,
        private val schedulers: SchedulersProvider) : ListDelegationAdapter<MutableList<ContentDisplayable>>() {

    private val deltaPositionLoading = 5

    private var disposable: Disposable? = null

    private var needsScrolling = true

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(NewsMainContentDisplayableAdapter(openCurrentContent))
                .addDelegate(ProgressAdapter())
                .addDelegate(ErrorAdapter(loadNextPage))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int, payloads: MutableList<Any?>?) {
        super.onBindViewHolder(holder, position, payloads)
        if (position == items.size - deltaPositionLoading && !isError()) loadNextPage.invoke()
    }

    fun setNewData(model: PaginatorData<ContentDisplayable>) {
        disposable?.dispose()
        val newList: MutableList<ContentDisplayable> = mutableListOf()
        newList.addAll(model.content)
        Timber.d("setNewData %s", "${model.content.size} ${model.state.name}")
        when (model.state) {
            ContentState.DATA -> {}
            ContentState.PROGRESS -> newList.add(ContentDisplayable.Progress())
            ContentState.ERROR -> newList.add(ContentDisplayable.Error())
        }
        when (model.recyclerUpdate) {
            RecyclerUpdate.DIFF_UTIL -> disposable = notifyDiffUtil(newList)
            RecyclerUpdate.NOTIFY -> notifyRanges(newList)
        }

    }

    fun disposeSubscription() {
        disposable?.dispose()
    }

    private fun isError() = items.last() is ContentDisplayable.Error

    private fun notifyDiffUtil(newList: List<ContentDisplayable>): Disposable {
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

    private fun notifyRanges(newList: List<ContentDisplayable>) {
        items.apply { clear() }.addAll(newList)
        notifyDataSetChanged()
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