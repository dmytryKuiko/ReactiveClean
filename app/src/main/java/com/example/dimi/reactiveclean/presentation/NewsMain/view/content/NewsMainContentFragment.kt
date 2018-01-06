package com.example.dimi.reactiveclean.presentation.NewsMain.view.content


import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseFragment
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.presentation.NewsMain.NewsMainContentAdapter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.NewsMainContentPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_news_main_content.*
import timber.log.Timber
import javax.inject.Inject

class NewsMainContentFragment : BaseFragment() {
    @Inject
    lateinit var presenter: NewsMainContentPresenter

    @Inject
    lateinit var schedulers: SchedulersProvider

    private val contentAdapter by lazy {
        NewsMainContentAdapter(
                loadNextPage = presenter::loadNextContentPage,
                scrollTo = this::scrollToLastPosition,
                openCurrentContent = this::openCustomTabs,
                schedulers = schedulers
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_main_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(fragment_news_main_content_recycler_view) {
            layoutManager = LinearLayoutManager(activity)
            adapter = contentAdapter
            setHasFixedSize(true)
        }

        presenter.subscribeSearchText(RxTextView.textChangeEvents(searchText)
                .map { it.text().toString() })

        presenter.getData().observe(this, Observer { data ->
            data?.let {
                it.paginatorData?.let {
                    contentAdapter.setNewData(it)
                }

                it.showDatabaseMessage?.let {
                    var a = 2
                    a++
                }

                it.showEmptyProgress?.let {
                    var a = 2
                    a++
                }

                it.showEmptyView?.let {
                    var a = 2
                    a++
                }

                it.showRefreshProgress?.let {
                    var a = 2
                    a++
                }
            }
        })

        content_refresh_button.setOnClickListener { presenter.refreshContent() }
    }

    override fun onDestroyView() {
        presenter.setVisibleItem(
                (fragment_news_main_content_recycler_view.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition())

        super.onDestroyView()
    }

    override fun onDestroy() {
        contentAdapter.disposeSubscription()
        presenter.disposeRxBinding()
        super.onDestroy()
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? NewsMainComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }

    private fun scrollToLastPosition() {
        fragment_news_main_content_recycler_view.scrollToPosition(presenter.getVisibleItem())
    }

    private fun openCustomTabs(content: ContentDisplayable.Content) {
        val customTabs = CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .build()

        customTabs.launchUrl(activity, Uri.parse(content.url))
    }
}
