package com.example.dimi.reactiveclean.presentation.main.view.sectionChosen

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

    @Inject
    lateinit var presenter: SectionChosenPresenter

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
        return inflater.inflate(R.layout.fragment_section_chosen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(fragment_section_chosen_recycler_view) {
            layoutManager = LinearLayoutManager(activity)
            adapter = contentAdapter
            setHasFixedSize(true)
        }

        content_toolbar.findViewById<ImageButton>(R.id.general_toolbar_refresh_button).setOnClickListener { presenter.refreshContent() }
        content_toolbar.findViewById<TextView>(R.id.general_toolbar_title).text = presenter.getSectionChosen()

        presenter.getData().observe(this, Observer { data ->
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

        presenter.getNetworkError().observe(this, Observer { it?.displayToast(activity!!) })
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isRemoving) {
            ComponentManager.releaseTempComponent(this)
        }
    }

    override fun injectModule(context: Context) {
        (ComponentManager.getTempComponent(context, this, null) as SectionChosenComponent).inject(this)
    }

    private fun scrollToLastPosition() {
        fragment_section_chosen_recycler_view.scrollToPosition(presenter.getVisibleItem())
    }
}
