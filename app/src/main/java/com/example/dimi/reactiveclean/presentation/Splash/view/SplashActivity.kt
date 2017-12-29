package com.example.dimi.reactiveclean.presentation.Splash.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.NewsMain.view.NewsMainActivity
import com.example.dimi.reactiveclean.presentation.Splash.presenter.SplashPresenter
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialActivity
import com.example.dimi.reactiveclean.utils.ComponentManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var presenter: SplashPresenter

//    private val appNavigator: SupportFragmentNavigator by lazy {
//        ExtendedNavigator(this, R.id.splash_activity_layout)
//    }

    private val appNavigator = object : SupportAppNavigator(this@SplashActivity, R.id.splash_activity_layout) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? =
                when(screenKey) {
                    RouterConstants.TUTORIAL_ACTIVITY -> Intent(this@SplashActivity, TutorialActivity::class.java)
                    RouterConstants.NEWS_MAIN_ACTIVITY -> Intent(this@SplashActivity, NewsMainActivity::class.java)
                    else -> null
                }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
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
