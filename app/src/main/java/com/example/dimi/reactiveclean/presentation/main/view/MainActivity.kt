package com.example.dimi.reactiveclean.presentation.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import com.example.dimi.reactiveclean.presentation.main.view.drawer.DrawerFragment
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.main.view.search.SearchFragment
import com.example.dimi.reactiveclean.presentation.BaseActivity
import com.example.dimi.reactiveclean.extensions.navigator.ExtendedNavigator
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.presentation.main.presenter.MainPresenter
import com.example.dimi.reactiveclean.presentation.main.view.about.AboutFragment
import com.example.dimi.reactiveclean.presentation.main.view.sectionChosen.SectionChosenFragment
import com.example.dimi.reactiveclean.utils.ComponentManager
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.main_activity_fragment_container) as? BaseFragment

    private val drawerFragment
        get() = supportFragmentManager.findFragmentById(R.id.main_activity_drawer_container) as? DrawerFragment

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var menuDisposable: Disposable? = null

    private val appNavigator = object : ExtendedNavigator(
        activity = this@MainActivity,
        containerId = R.id.main_activity_fragment_container
    ) {

        override fun createActivityIntent(
            context: Context?,
            screenKey: String?,
            data: Any
        ): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            RouterConstants.MAIN_SCREEN -> MainFragment()
            RouterConstants.SECTION_CHOSEN_SCREEN -> initTempComponent(
                SectionChosenFragment(),
                data
            )
            RouterConstants.SEARCH_SCREEN -> initTempComponent(SearchFragment())
            RouterConstants.SEARCH_CHOSEN_SCREEN -> initTempComponent(SectionChosenFragment(), data)
            RouterConstants.ABOUT_SCREEN -> initTempComponent(AboutFragment())
            else -> null
        }

        override fun createCustomTabsIntent(url: String): CustomTabsIntent =
            CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_fragment_container, MainFragment())
                .replace(R.id.main_activity_drawer_container, DrawerFragment())
                .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(appNavigator)
        menuDisposable = presenter.isMenuOpen()
            .subscribe(this::openDrawer)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
        menuDisposable?.dispose()
    }

    override fun onBackPressed() {
        if (main_activity_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            openDrawer(false)
        } else {
            currentFragment?.onBackPressed()
        }
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }

    private fun initTempComponent(fragment: Fragment, data: Any? = null): Fragment {
        ComponentManager.getTempComponent(activity = this, fragment = fragment, data = data)
        return fragment
    }

    private fun openDrawer(open: Boolean) {
        main_activity_drawer_layout.postDelayed({
            if (open) {
                main_activity_drawer_layout.openDrawer(GravityCompat.START, true)
            } else {
                main_activity_drawer_layout.closeDrawer(GravityCompat.START, true)
            }
        }, 150)

    }
}
