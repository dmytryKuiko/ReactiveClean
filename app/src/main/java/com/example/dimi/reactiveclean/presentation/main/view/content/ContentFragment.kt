package com.example.dimi.reactiveclean.presentation.main.view.content


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.ImageButton
import android.widget.TextView

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.di.components.MainComponent
import com.example.dimi.reactiveclean.extensions.displayToast
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.presentation.main.adapters.NewsMainContentAdapter
import com.example.dimi.reactiveclean.presentation.main.presenter.content.ContentPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import kotlinx.android.synthetic.main.fragment_content.*
import javax.inject.Inject

class ContentFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_content

    @Inject
    lateinit var presenter: ContentPresenter

    @Inject
    lateinit var schedulers: SchedulersProvider

    private val contentAdapter by lazy {
        NewsMainContentAdapter(
            loadNextPage = presenter::loadNextContentPage,
            openCurrentContent = presenter::openCurrentContent,
            schedulers = schedulers
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initToolbar()
        subscribePresenter()
    }

    override fun onDestroy() {
        contentAdapter.disposeSubscription()
        super.onDestroy()
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? MainComponent)
                ?: throw ClassCastException("Component is not an instance of MainComponent")
        component.inject(this)
    }

    private fun initRecyclerView() {
        with(fragment_news_main_content_recycler_view) {
            layoutManager = LinearLayoutManager(activity)
            adapter = contentAdapter
            setHasFixedSize(true)
        }
    }

    private fun initToolbar() {
        with(content_toolbar) {
            findViewById<ImageButton>(R.id.general_toolbar_refresh_button)
                .setOnClickListener { presenter.refreshClicked() }
            (findViewById<TextView>(R.id.general_toolbar_title)).text = "Content"
            findViewById<Toolbar>(R.id.general_toolbar).setNavigationOnClickListener { presenter.openMenuClicked() }
            findViewById<ImageButton>(R.id.general_toolbar_search_button)
                .setOnClickListener { presenter.searchClicked() }
        }
    }

    private fun subscribePresenter() {
        with(presenter) {
            getData().observe(this@ContentFragment, Observer { data ->
                data?.let {
                    it.paginatorModelData?.let { contentAdapter.setNewData(it) }
                    it.showEmptyProgress?.let { refresh_progress.visible(it) }
                    it.showEmptyView?.let { empty_view.visible(it) }
                    it.showRefreshProgress?.let { refresh_progress.visible(it) }
                }
            })

            getSingleEventData().observe(
                this@ContentFragment,
                Observer { it?.displayToast(activity) })
        }
    }
}
