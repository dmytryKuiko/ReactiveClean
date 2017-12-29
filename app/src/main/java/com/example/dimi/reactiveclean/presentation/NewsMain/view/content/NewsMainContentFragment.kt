package com.example.dimi.reactiveclean.presentation.NewsMain.view.content


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseFragment
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.models.content.ContentRecyclerData
import com.example.dimi.reactiveclean.presentation.NewsMain.NewsMainContentAdapter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.NewsMainContentPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import kotlinx.android.synthetic.main.fragment_news_main_content.*
import javax.inject.Inject

class NewsMainContentFragment : BaseFragment() {
    @Inject
    lateinit var presenter: NewsMainContentPresenter

    private val contentAdapter = NewsMainContentAdapter()

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

        presenter.listenRecyclerScrollAndItems(
                RxRecyclerView.scrollEvents(fragment_news_main_content_recycler_view)
                        .map {
                            val lastVisible = (it.view().layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            val allPositions = it.view().adapter.itemCount
                            ContentRecyclerData(lastVisible, allPositions)
                        }
        )

        presenter.getData().observe(this, Observer {
            it?.let { contentAdapter.updateItems(it) }
        })
    }

    override fun onDestroy() {
        contentAdapter.unsubscribe()
        super.onDestroy()
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? NewsMainComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }
}
