package com.example.dimi.reactiveclean.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import com.example.dimi.reactiveclean.presentation.Main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialActivity

object ComponentManager {

    private var appComponent: AppComponent? = null

    private val componentMap: MutableMap<String, BaseComponent<Context>> = hashMapOf()

    @Synchronized
    fun initAppComponent(app: App) {
        if(appComponent == null) {
            appComponent = DaggerAppComponent.builder().application(app)
                    .baseUrlRetrofit("https://newsapi.org/v2/")
                    .build()
        }
    }

    fun getComponent(activity: Context): BaseComponent<Context> {
        val name = getName(activity)
        var component = componentMap[name]
        if (component == null) {
            component = createComponent(name)
            storeComponent(name, component)
        }
        return component
    }

    fun releaseComponent(activity: Context) {
        val name = getName(activity)
        componentMap.remove(name)
    }

    private fun getName(activity: Context): String =
            activity::class.qualifiedName ?: throw PackageManager.NameNotFoundException("For activity $activity")

    private fun createComponent(name: String): BaseComponent<Context> =
            when(name) {
                MainActivity::class.qualifiedName ->
                    appComponent?.mainComponentBuilder()?.build() as BaseComponent<Context>
                TutorialActivity::class.qualifiedName ->
                    appComponent?.tutorialComponentBuilder()?.build() as BaseComponent<Context>
                else -> TODO()
            }

    private fun storeComponent(name: String, component: BaseComponent<Context>) {
        componentMap.put(name, component)
    }
}