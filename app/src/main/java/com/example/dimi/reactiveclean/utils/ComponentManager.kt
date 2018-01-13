package com.example.dimi.reactiveclean.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.BaseComponent
import com.example.dimi.reactiveclean.di.TempComponent
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.di.modules.SectionChosenModule
import com.example.dimi.reactiveclean.models.section.SectionChosenModel
import com.example.dimi.reactiveclean.presentation.main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.splash.view.SplashActivity
import com.example.dimi.reactiveclean.presentation.tutorial.view.TutorialActivity

object ComponentManager {

    const val API_URL = "http://content.guardianapis.com/"

    private var appComponent: AppComponent? = null

    private val componentMap: MutableMap<String, BaseComponent<Context>> = hashMapOf()

    private val tempComponentMap: MutableMap<String, TempComponent> = hashMapOf()

    @Synchronized
    fun initAppComponent(app: App) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().application(app)
                    .baseUrlRetrofit(API_URL)
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

    fun getTempComponent(activity: Context, fragment: Fragment, data: Any?): TempComponent {
        val name = getName(fragment)
        val componentName = getName(activity)
        var component = tempComponentMap[name]
        if (component == null) {
            component = createTempComponent(componentName, name, data)
            storeTempComponent(name, component)
        }
        return component
    }

    fun releaseComponent(activity: Context) {
        val name = getName(activity)
        componentMap.remove(name)
    }

    fun releaseTempComponent(fragment: Fragment) {
        val name = getName(fragment)
        tempComponentMap.remove(name)
    }

    private fun getName(creator: Any): String =
            creator::class.qualifiedName ?: throw PackageManager.NameNotFoundException("For activity $creator")

    private fun createComponent(name: String): BaseComponent<Context> {
        val component = appComponent ?: throw NullPointerException("App Component is null")
        return when (name) {
            SplashActivity::class.qualifiedName ->
                component.splashComponentBuilder().build() as BaseComponent<Context>
            TutorialActivity::class.qualifiedName ->
                component.tutorialComponentBuilder().build() as BaseComponent<Context>
            MainActivity::class.qualifiedName ->
                component.newsMainComponentBuilder().build() as BaseComponent<Context>
            else -> throw UninitializedPropertyAccessException("This component is not initialized yet")
        }
    }

    private fun createTempComponent(componentName: String, tempName: String, data: Any?): TempComponent {

        return when(componentName) {
            MainActivity::class.qualifiedName -> {
                val component = componentMap[componentName] as? NewsMainComponent ?:
                        throw NullPointerException("Parent component is null for temp component")
                val model = data as? SectionChosenModel ?: throw IllegalArgumentException("Wrong parameter passed")
                component.sectionChosenBuilder()
                        .sectionUrl(model.url)
                        .sectionTitle(model.title)
                        .build()
            }

            else -> throw UninitializedPropertyAccessException("This component is not initialized yet")
        }
    }

    private fun storeComponent(name: String, component: BaseComponent<Context>) {
        componentMap.put(name, component)
    }

    private fun storeTempComponent(name: String, component: TempComponent) {
        tempComponentMap.put(name, component)
    }
}