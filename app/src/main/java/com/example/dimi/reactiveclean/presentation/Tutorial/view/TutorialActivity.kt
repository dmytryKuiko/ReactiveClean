package com.example.dimi.reactiveclean.presentation.tutorial.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.dimi.reactiveclean.navigation.tutorial.TutorialMainNavigator
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseActivity
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.main.view.NewsMainActivity
import com.example.dimi.reactiveclean.utils.ComponentManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class TutorialActivity : BaseActivity() {

    @Inject lateinit var mainNavigator: TutorialMainNavigator

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val appNavigator = object : SupportAppNavigator(this@TutorialActivity, R.id.tutorial_activity_container_layout) {

        override fun createActivityIntent(screenKey: String, data: Any?): Intent? = when (screenKey) {
            RouterConstants.NEWS_MAIN_ACTIVITY -> Intent(this@TutorialActivity, NewsMainActivity::class.java)
            else -> null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            RouterConstants.TUTORIAL_FIRST_SCREEN -> TutorialFirstFragment()
            RouterConstants.TUTORIAL_SECOND_SCREEN -> TutorialSecondFragment()
            RouterConstants.TUTORIAL_THIRD_SCREEN -> TutorialThirdFragment()
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(appNavigator)
        mainNavigator.startNavigation()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }
}
