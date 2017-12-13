package com.example.dimi.reactiveclean

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity

class ComponentManager {
    private val mapComponent: MutableMap<String, BaseComponent<Context>> = hashMapOf()

    fun getComponent(activity: Activity, application: Application): BaseComponent<Context> {
        var component = mapComponent[activity::class.java.canonicalName]
        if (component == null) {
            component = createComponent(activity, application)
            storeComponent(activity, component)
        }
        return component
    }

    fun deleteComponent(activity: Activity) {
        mapComponent.remove(activity::class.java.canonicalName)
    }

   private fun createComponent(activity: Activity, application: Application): BaseComponent<Context> {
       val component: BaseComponent<Context>
        if (activity::class.java.canonicalName == MainActivity::class.java.canonicalName) {
            return (application as App).appComponent.provideMainActivityBuilder().build() as BaseComponent<Context>
        } else TODO()
    }

    private fun storeComponent(activity: Activity, component: BaseComponent<Context>) {
        mapComponent.put(activity::class.java.canonicalName, component)
    }
}