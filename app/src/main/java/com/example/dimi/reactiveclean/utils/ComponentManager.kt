package com.example.dimi.reactiveclean.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.BaseComponent
import com.example.dimi.reactiveclean.di.TempComponent
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import com.example.dimi.reactiveclean.di.components.MainComponent
import com.example.dimi.reactiveclean.models.section.ContentChosen
import com.example.dimi.reactiveclean.presentation.BaseActivity
import com.example.dimi.reactiveclean.presentation.main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.main.view.about.AboutFragment
import com.example.dimi.reactiveclean.presentation.main.view.search.SearchFragment
import com.example.dimi.reactiveclean.presentation.main.view.sectionChosen.SectionChosenFragment
import com.example.dimi.reactiveclean.presentation.splash.view.SplashActivity
import com.example.dimi.reactiveclean.presentation.tutorial.view.TutorialActivity

object ComponentManager {

    const val API_URL = "http://content.guardianapis.com/"

    private var appComponent: AppComponent? = null

    private val componentMap: MutableMap<String, BaseComponent<BaseActivity>> = hashMapOf()

    private val tempComponentMap: MutableMap<String, TempComponent> = hashMapOf()

    @Synchronized
    fun initAppComponent(app: App) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().application(app)
                .baseUrlRetrofit(API_URL)
                .build()
        }
    }

    fun getComponent(context: Context): BaseComponent<BaseActivity> {
        val name = getName(context)
        var component = componentMap[name]
        if (component == null) {
            component = createComponent(name) as BaseComponent<BaseActivity>
            storeComponent(name, component)
        }
        return component
    }

    fun getTempComponent(activity: Context, fragment: Fragment, data: Any? = null): TempComponent {
        val tempComponentName = getName(fragment)
        val componentName = getName(activity)
        var component = tempComponentMap[tempComponentName]
        if (component == null) {
            component = createTempComponent(componentName, tempComponentName, data)
            storeTempComponent(tempComponentName, component)
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
        creator::class.qualifiedName
                ?: throw PackageManager.NameNotFoundException("For activity $creator")


    private fun createComponent(name: String): BaseComponent<out BaseActivity> {
        val component = appComponent ?: throw NullPointerException("App Component is null")
        return when (name) {
            SplashActivity::class.qualifiedName ->
                component.splashComponentBuilder().build()
            TutorialActivity::class.qualifiedName ->
                component.tutorialComponentBuilder().build()
            MainActivity::class.qualifiedName ->
                component.mainComponentBuilder().build()
            else -> throw UninitializedPropertyAccessException("This component is not initialized yet")
        }
    }

    private fun createTempComponent(
        componentName: String,
        tempComponentName: String,
        data: Any?
    ): TempComponent = when (componentName) {
        MainActivity::class.qualifiedName -> {
            val component =
                componentMap[componentName] as? MainComponent ?: throw NullPointerException(
                    "Parent component is null for temp component"
                )
            when (tempComponentName) {
                SectionChosenFragment::class.qualifiedName -> {
                    val model = data as? ContentChosen
                            ?: throw IllegalArgumentException("Wrong parameter passed")

                    component.sectionChosenBuilder()
                        .sectionChosenModel(model)
                        .build()
                }

                SearchFragment::class.qualifiedName -> {
                    component.searchBuilder().build()
                }

                AboutFragment::class.qualifiedName -> {
                    component.aboutBuilder().build()
                }

                else -> throw UninitializedPropertyAccessException(
                    "This component is not initialized yet for $tempComponentName"
                )
            }
        }
        else -> throw UninitializedPropertyAccessException("This component is not initialized yet")
    }

    private fun storeComponent(name: String, component: BaseComponent<BaseActivity>) {
        componentMap[name] = component
    }

    private fun storeTempComponent(name: String, component: TempComponent) {
        tempComponentMap[name] = component
    }
}