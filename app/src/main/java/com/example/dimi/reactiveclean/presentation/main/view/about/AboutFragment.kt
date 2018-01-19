package com.example.dimi.reactiveclean.presentation.main.view.about


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageButton
import android.widget.TextView

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.di.components.AboutComponent
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.presenter.about.AboutPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject


class AboutFragment : BaseFragment() {

    @Inject
    lateinit var presenter: AboutPresenter

    override val layoutId: Int
        get() = R.layout.fragment_about

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(fragment_about_toolbar) {
            findViewById<TextView>(R.id.general_toolbar_title)
                .text = "About"
            findViewById<ImageButton>(R.id.general_toolbar_refresh_button)
                .visible(false)
            findViewById<ImageButton>(R.id.general_toolbar_search_button)
                .visible(false)
            findViewById<Toolbar>(R.id.general_toolbar)
                .setNavigationOnClickListener { presenter.openMenuClicked() }

        }
    }

    override fun injectModule(context: Context) {
        (ComponentManager.getTempComponent(context, this) as AboutComponent).inject(this)
    }

    override fun onBackPressed() {
        presenter.onBackClicked()
    }
}
