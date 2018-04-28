package com.example.dimi.reactiveclean.presentation.main.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelData
import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.ContentState
import com.example.dimi.reactiveclean.utils.DiffUtilContent
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import timber.log.Timber

class NewsMainContentAdapter(
    private val loadNextPage: () -> Unit,
    val openCurrentContent: (ContentDisplayable.Content) -> Unit,
    private val schedulers: SchedulersProvider
) : ListDelegationAdapter<MutableList<ContentDisplayable>>() {

    private var disposable: Disposable? = null

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(NewsMainContentDisplayableAdapter(openCurrentContent))
            .addDelegate(ProgressAdapter())
            .addDelegate(ErrorAdapter(loadNextPage))
            .setFallbackDelegate(AllDataAdapter())
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any?>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (position == items.size - DELTA_POSITION_LOADING && isContent()) loadNextPage.invoke()
    }

    fun setNewData(model: PaginatorModelData<ContentDisplayable>) {
        disposable?.dispose()
        val newList: MutableList<ContentDisplayable> = mutableListOf()
        newList.addAll(model.content)
        Timber.d("setNewData %s", "${model.content.size} ${model.state.name}")
        when (model.state) {
            ContentState.DATA -> {
            }
            ContentState.PROGRESS -> newList.add(ContentDisplayable.Progress())
            ContentState.ERROR -> newList.add(ContentDisplayable.Error())
            ContentState.ALL_DATA -> newList.add(ContentDisplayable.AllContent())
        }
        when (model.recyclerUpdate) {
            RecyclerUpdate.DIFF_UTIL -> disposable = notifyDiffUtil(newList)
            RecyclerUpdate.NOTIFY -> notifyRanges(newList)
        }

    }

    fun disposeSubscription() {
        disposable?.dispose()
    }

    private fun isContent() = items.last() is ContentDisplayable.Content

    private fun notifyDiffUtil(newList: List<ContentDisplayable>): Disposable {
        return Single.fromCallable { DiffUtil.calculateDiff(DiffUtilContent(items, newList)) }
            .compose(::composeSchedulers)
            .subscribe(
                {
                    items.apply { clear() }.addAll(newList)
                    it.dispatchUpdatesTo(this@NewsMainContentAdapter)
                },
                { throw Exception(it.message) }
            )
    }

    private fun notifyRanges(newList: List<ContentDisplayable>) {
        items.apply { clear() }.addAll(newList)
        notifyDataSetChanged()
    }

    private fun composeSchedulers(single: Single<DiffUtil.DiffResult>): Single<DiffUtil.DiffResult> =
        single.subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())

    companion object {
        const val DELTA_POSITION_LOADING = 5
    }
}