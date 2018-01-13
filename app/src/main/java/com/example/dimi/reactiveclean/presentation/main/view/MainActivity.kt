package com.example.dimi.reactiveclean.presentation.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseActivity
import com.example.dimi.reactiveclean.extensions.navigator.ExtendedNavigator
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.presentation.main.view.content.ContentFragment
import com.example.dimi.reactiveclean.presentation.main.view.section.SectionFragment
import com.example.dimi.reactiveclean.presentation.main.view.sectionChosen.SectionChosenFragment
import com.example.dimi.reactiveclean.utils.ComponentManager
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_news_main.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.news_main_activity_container) as? BaseFragment

    @Inject
    lateinit var presenter: NewsMainPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var menuDisposable: Disposable? = null

    private val appNavigator = object : ExtendedNavigator(
            activity = this@MainActivity,
            containerId = R.id.news_main_activity_container
    ) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            RouterConstants.MAIN_SCREEN -> MainFragment()
            RouterConstants.SECTION_CHOSEN_SCREEN -> createFragmentAndInitComponent(data)
            else -> null
        }

        override fun createCustomTabsIntent(url: String): CustomTabsIntent? =
                CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setShowTitle(true)
                        .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(appNavigator)
        menuDisposable = presenter.isMenuOpen().subscribe(
                {
                    var a = 2
                    a++
                },
                {
                    var a = 2
                    a++
                }
        )
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
        menuDisposable?.dispose()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }

    private fun createFragmentAndInitComponent(data: Any?): Fragment {
        val fragment = SectionChosenFragment()
        ComponentManager.getTempComponent(activity = this, fragment = fragment, data = data)
        return fragment
    }
}
