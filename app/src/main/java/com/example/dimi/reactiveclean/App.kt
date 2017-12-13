package com.example.dimi.reactiveclean

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject

class App : Application() {

    lateinit var appComponent: AppComponent
    private set

    @Inject
    lateinit var componentManager: ComponentManager


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this)
                .baseUrlRetrofit("https://newsapi.org/v2/")
                .build()
        appComponent.inject(this)
    }

    fun getMainActivityComponent(activity: Activity): BaseComponent<Context> {
        return componentManager.getComponent(activity, this)
    }

    fun releaseMainActivityComponent(activity: Activity) {
        componentManager.deleteComponent(activity)
    }
}