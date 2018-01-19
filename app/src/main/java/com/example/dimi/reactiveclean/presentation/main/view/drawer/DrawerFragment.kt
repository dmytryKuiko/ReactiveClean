package com.example.dimi.reactiveclean.presentation.main.view.drawer


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.di.components.MainComponent
import com.example.dimi.reactiveclean.models.MenuItem
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.presenter.drawer.DrawerPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_drawer.*
import javax.inject.Inject

class DrawerFragment : BaseFragment() {

    @Inject
    lateinit var presenter: DrawerPresenter

    override val layoutId: Int
        get() = R.layout.fragment_drawer

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_drawer_news.tag = MenuItem.News
        fragment_drawer_about.tag = MenuItem.About

        fragment_drawer_news.setOnClickListener { presenter.menuItemClicked(it.tag as MenuItem) }
        fragment_drawer_about.setOnClickListener { presenter.menuItemClicked(it.tag as MenuItem) }

        presenter.getData().observe(this, Observer {
            it?.let {
                updateSelectedItem(it)
            }
        })
    }

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? MainComponent)
                ?: throw ClassCastException("Component is not an instance of MainComponent")
        component.inject(this)
    }

    private fun updateSelectedItem(item: MenuItem) {
        val childCount = fragment_drawer_container.childCount
        (0 until childCount)
            .map { fragment_drawer_container.getChildAt(it) }
            .forEach { itemView ->
                itemView.isSelected = itemView.tag == item
            }
    }
}
