package com.example.dimi.reactiveclean.presentation.NewsMain.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.view.content.NewsMainContentFragment
import com.example.dimi.reactiveclean.presentation.NewsMain.view.sections.NewsMainSectionsFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialFirstFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialSecondFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialThirdFragment
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_news_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class NewsMainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var presenter: NewsMainPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val appNavigator = object : SupportAppNavigator(this@NewsMainActivity, R.id.news_main_activity_container) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            RouterConstants.NEWS_MAIN_SECTIONS_SCREEN -> NewsMainSectionsFragment()
            RouterConstants.NEWS_MAIN_CONTENT_SCREEN -> NewsMainContentFragment()
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)

        news_main_bottom_bar.setOnNavigationItemSelectedListener(this@NewsMainActivity)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.content_bottom_bar -> {
                    presenter.onContentClicked()
                    true
                }
                R.id.sections_bottom_bar -> {
                    presenter.onSectionsClicked()
                    true
                }
                else -> false
            }
}
