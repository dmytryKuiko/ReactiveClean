package com.example.dimi.reactiveclean.presentation.Tutorial.view

import android.os.Bundle
import com.example.dimi.reactiveclean.Navigator.Main.NavigatorBuilder
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.utils.ComponentManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class TutorialActivity : BaseActivity() {

    @Inject lateinit var router: Router

    @Inject lateinit var navigatorHolder: NavigatorHolder

    @Inject lateinit var navBuilder: NavigatorBuilder

    private val appNavigator: SupportFragmentNavigator by lazy {
        navBuilder.buildSupportFragmentNavigator(supportFragmentManager, R.id.tutorial_activity_container_layout, this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(appNavigator)
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
