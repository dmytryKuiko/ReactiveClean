package com.example.dimi.reactiveclean.presentation.NewsMain.view.content


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseFragment
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.extensions.displayToast
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.presentation.NewsMain.adapters.NewsMainContentAdapter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.ContentPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_news_main_content.*
import javax.inject.Inject

class ContentFragment : BaseFragment() {
    @Inject
    lateinit var presenter: ContentPresenter

    @Inject
    lateinit var schedulers: SchedulersProvider

    private val contentAdapter by lazy {
        NewsMainContentAdapter(
                loadNextPage = presenter::loadNextContentPage,
                scrollTo = this::scrollToLastPosition,
                openCurrentContent = presenter::openCurrentContent,
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

        content_toolbar.findViewById<ImageButton>(R.id.general_toolbar_refresh_button).setOnClickListener { presenter.refreshContent() }
        content_toolbar.findViewById<TextView>(R.id.general_toolbar_title).text = "Content"

        presenter.subscribeSearchText(RxTextView.textChangeEvents(searchText)
                .map { it.text().toString() })

        presenter.getData().observe(this, Observer { data ->
            data?.let {
                it.paginatorData?.let {
                    contentAdapter.setNewData(it)
                }

                it.showEmptyProgress?.let {
                    refresh_progress.visible(it)
                }

                it.showEmptyView?.let {
                    empty_view.visible(it)
                }

                it.showRefreshProgress?.let {
                    refresh_progress.visible(it)
                }
            }
        })

        presenter.getSingleEventData().observe(this, Observer { it?.displayToast(activity!!) })
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
                throw ClassCastException("Component is not an instance of NewsMainComponent")
        component.inject(this)
    }

    private fun scrollToLastPosition() {
        fragment_news_main_content_recycler_view.scrollToPosition(presenter.getVisibleItem())
    }
}