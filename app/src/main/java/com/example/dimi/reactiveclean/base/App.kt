package com.example.dimi.reactiveclean.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.dimi.reactiveclean.ComponentManager
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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
//        if (firstScreenComponent == null) {
//            firstScreenComponent = appComponent.provideMainActivityBuilder().build()
//        }
//        return firstScreenComponent!!
        return componentManager.getComponent(activity, this)
    }

    fun releaseMainActivityComponent(activity: Activity) {
//        firstScreenComponent = null
        componentManager.deleteComponent(activity)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}