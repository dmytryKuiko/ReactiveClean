package com.example.dimi.reactiveclean.presentation.Tutorial.view

import android.os.Bundle
import com.example.dimi.reactiveclean.utils.NavigatorBuilder
import com.example.dimi.reactiveclean.navigation.Tutorial.TutorialMainNavigator
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Navigators.ExtendedNavigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class TutorialActivity : BaseActivity() {

    @Inject lateinit var mainNavigator: TutorialMainNavigator

    @Inject lateinit var navigatorHolder: NavigatorHolder

    //@Inject lateinit var navBuilder: NavigatorBuilder

    private val appNavigator: SupportFragmentNavigator by lazy {
        ExtendedNavigator(this, R.id.tutorial_activity_container_layout)
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
