package com.example.dimi.reactiveclean.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity

class ComponentManager {
    private val mapComponent: MutableMap<String, BaseComponent<Context>> = hashMapOf()

    fun getComponent(activity: Activity, application: Application): BaseComponent<Context> {
        val canonicalName = activity::class.java.canonicalName
        var component = mapComponent[canonicalName]
        if (component == null) {
            component = createComponent(canonicalName, application)
            storeComponent(canonicalName, component)
        }
        return component
    }

    fun deleteComponent(activity: Activity) {
        mapComponent.remove(activity::class.java.canonicalName)
    }

   private fun createComponent(name: String, application: Application): BaseComponent<Context> =
           when(name) {
               MainActivity::class.java.canonicalName ->
                   (application as App).appComponent.provideMainActivityBuilder().build() as BaseComponent<Context>
               else -> TODO()
           }

    private fun storeComponent(name: String, component: BaseComponent<Context>) {
        mapComponent.put(name, component)
    }
}