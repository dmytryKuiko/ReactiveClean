package com.example.dimi.reactiveclean.presentation.main.view.section


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.extensions.displayToast
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.presentation.main.adapters.NewsMainSectionsAdapter
import com.example.dimi.reactiveclean.presentation.main.presenter.section.SectionPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_section.*
import javax.inject.Inject

class SectionFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_section

    @Inject
    lateinit var presenter: SectionPresenter

    private val sectionAdapter by lazy {
        NewsMainSectionsAdapter(presenter::openCurrentSection)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections_toolbar.findViewById<TextView>(R.id.general_toolbar_title).text = "Sections"

        sections_toolbar.findViewById<Toolbar>(R.id.general_toolbar).setNavigationOnClickListener { presenter.openMenu()}

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
