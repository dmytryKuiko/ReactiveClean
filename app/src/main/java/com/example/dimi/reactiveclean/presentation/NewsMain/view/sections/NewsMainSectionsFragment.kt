package com.example.dimi.reactiveclean.presentation.NewsMain.view.sections


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
import com.example.dimi.reactiveclean.presentation.NewsMain.adapters.NewsMainSectionsAdapter
import com.example.dimi.reactiveclean.presentation.NewsMain.adapters.NewsMainSectionsDisplayableAdapter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections.NewsMainSectionsPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_news_main_sections.*
import timber.log.Timber
import javax.inject.Inject

class NewsMainSectionsFragment : BaseFragment() {
    @Inject
    lateinit var presenter: NewsMainSectionsPresenter

    private val sectionAdapter by lazy {
        NewsMainSectionsAdapter(presenter::openCurrentSection)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_main_sections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections_toolbar.findViewById<TextView>(R.id.general_toolbar_title).text = "Sections"

        with(sections_recycler_view) {
            layoutManager = LinearLayoutManager(activity)
            adapter = sectionAdapter
            setHasFixedSize(true)
        }

        sections_toolbar.findViewById<ImageButton>(R.id.general_toolbar_refresh_button).visible(false)

        presenter.getData().observe(this, Observer {
            it?.let {
                sectionAdapter.setNewData(it)
            }
        })

        presenter.getSingleEventLiveData().observe(this, Observer {
            it?.displayToast(activity!!)
        })
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? NewsMainComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }
}
