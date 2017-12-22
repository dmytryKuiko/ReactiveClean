package com.example.dimi.reactiveclean.presentation.Splash.view

import android.os.Bundle
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.presentation.Splash.presenter.SplashPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Navigators.ExtendedNavigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var presenter: SplashPresenter

    private val appNavigator: SupportFragmentNavigator by lazy {
        ExtendedNavigator(this, R.id.splash_activity_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
        presenter.disposeSubscriptions()
        ComponentManager.releaseComponent(this)
    }
}
