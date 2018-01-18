package com.example.dimi.reactiveclean.presentation.main.view


import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.presenter.MainFragmentPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.presentation.main.view.content.ContentFragment
import com.example.dimi.reactiveclean.presentation.main.view.section.SectionFragment
import com.example.dimi.reactiveclean.presentation.main.view.section.SectionFragment_MembersInjector
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    private val tabKeys: List<String> = listOf(
        "${R.id.content_bottom_bar}",
        "${R.id.sections_bottom_bar}"
    )

    private lateinit var tabs: HashMap<String, BaseFragment>

    @Inject
    lateinit var presenter: MainFragmentPresenter

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? NewsMainComponent)
                ?: throw ClassCastException("Component is not an instance of NewsMainComponent")
        component.inject(this)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottom_bar.setOnNavigationItemSelectedListener(this::showFragment)

        if (!isVisible && savedInstanceState == null) {
            tabs = initFragments()
            childFragmentManager.beginTransaction()
                .add(R.id.fragment_main_container, tabs[tabKeys[0]], tabKeys[0])
                .add(R.id.fragment_main_container, tabs[tabKeys[1]], tabKeys[1])
                .commit()
            bottom_bar.selectedItemId = R.id.content_bottom_bar
        } else {
            tabs = findFragments()
        }
    }

    private fun initFragments(): HashMap<String, BaseFragment> = hashMapOf(
        tabKeys[0] to ContentFragment(),
        tabKeys[1] to SectionFragment()
    )

    private fun findFragments(): HashMap<String, BaseFragment> = hashMapOf(
        tabKeys[0] to childFragmentManager.findFragmentByTag(tabKeys[0]) as BaseFragment,
        tabKeys[1] to childFragmentManager.findFragmentByTag(tabKeys[1]) as BaseFragment
    )

    private fun showFragment(menuItem: MenuItem): Boolean {
        childFragmentManager.beginTransaction()
            .detach(tabs[tabKeys[0]])
            .detach(tabs[tabKeys[1]])
            .attach(tabs["${menuItem.itemId}"])
            .commit()
        return true
    }
}
