package com.example.dimi.reactiveclean.presentation.main.view.search


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.di.components.SearchComponent
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.adapters.SearchAdapter
import com.example.dimi.reactiveclean.presentation.main.presenter.search.SearchPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var schedulers: SchedulersProvider

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(
            schedulers = schedulers, callback = presenter::previousSearchClicked
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_search

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(fragment_search_recycler_view) {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        presenter.listenEditText(RxTextView.textChanges(fragment_search_edit_text)
            .map { it.toString() })

        presenter.listenEditTextModel(RxTextView.editorActionEvents(fragment_search_edit_text)
            .map { EditTextBindingModel(it.view().text.toString(), it.actionId()) })

        presenter.getData().observe(this, Observer {
            it?.let {
                searchAdapter.setNewData(it)
            }
        })
    }

    override fun injectModule(context: Context) {
        (ComponentManager.getTempComponent(context, this) as SearchComponent).inject(this)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchAdapter.disposeSubscription()
        presenter.disposeRxBinding()
        if (isRemoving) {
            presenter.disposeSubscriptions()
            ComponentManager.releaseTempComponent(this)
        }
    }
}
