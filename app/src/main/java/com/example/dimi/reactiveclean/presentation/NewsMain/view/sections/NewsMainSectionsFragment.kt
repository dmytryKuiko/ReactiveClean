package com.example.dimi.reactiveclean.presentation.NewsMain.view.sections


import android.content.Context
import android.os.BaseBundle
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseFragment
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections.NewsMainSectionsPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject

class NewsMainSectionsFragment : BaseFragment() {
    @Inject
    lateinit var presenter: NewsMainSectionsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_main_sections, container, false)
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? NewsMainComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }
}
