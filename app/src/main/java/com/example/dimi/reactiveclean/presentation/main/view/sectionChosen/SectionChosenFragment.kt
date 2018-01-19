package com.example.dimi.reactiveclean.presentation.main.view.sectionChosen

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.ImageButton
import android.widget.TextView
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.di.components.SectionChosenComponent
import com.example.dimi.reactiveclean.extensions.displayToast
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.presentation.main.adapters.NewsMainContentAdapter
import com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen.SectionChosenPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import kotlinx.android.synthetic.main.fragment_section_chosen.*
import javax.inject.Inject

class SectionChosenFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_section_chosen

    @Inject
    lateinit var presenter: SectionChosenPresenter

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
        super.onDestroy()
        if (isRemoving) {
            presenter.disposeSubscriptions()
            ComponentManager.releaseTempComponent(this)
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun injectModule(context: Context) {
        (ComponentManager.getTempComponent(context, this) as SectionChosenComponent).inject(
            this
        )
    }

    private fun initRecyclerView() {
        with(fragment_section_chosen_recycler_view) {
            layoutManager = LinearLayoutManager(activity)
            adapter = contentAdapter
            setHasFixedSize(true)
        }
    }

    private fun initToolbar() {

        with(content_toolbar) {
            findViewById<ImageButton>(R.id.general_toolbar_search_button)
                .setOnClickListener { presenter.searchClicked() }
            findViewById<ImageButton>(R.id.general_toolbar_refresh_button)
                .setOnClickListener { presenter.refreshContent() }
            findViewById<Toolbar>(R.id.general_toolbar)
                .setNavigationOnClickListener { presenter.openMenuClicked() }
        }
    }

    private fun subscribePresenter() {
        with(presenter) {
            getToolbarData().observe(this@SectionChosenFragment, Observer {
                it?.let {
                    with(content_toolbar) {
                        content_toolbar.findViewById<TextView>(R.id.general_toolbar_title).text =
                                it.title
                        findViewById<ImageButton>(R.id.general_toolbar_search_button).visible(it.searchVisibility)
                        findViewById<Toolbar>(R.id.general_toolbar).setNavigationIcon(it.navIcon)
                    }
                }
            })

            getData().observe(this@SectionChosenFragment, Observer { data ->
                data?.let {
                    it.paginatorModelData?.let {
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

            getNetworkError().observe(
                this@SectionChosenFragment,
                Observer { it?.displayToast(activity) })
        }
    }
}
