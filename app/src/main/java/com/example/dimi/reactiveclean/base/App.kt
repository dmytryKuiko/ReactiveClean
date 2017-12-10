package com.example.dimi.reactiveclean.base

import android.app.Activity
import android.app.Application
import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import com.example.dimi.reactiveclean.di.components.FirstScreenComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var firstScreenComponent: FirstScreenComponent? = null

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this)
                .baseUrlRetrofit("https://newsapi.org/v2/")
                .build()
        appComponent.inject(this)
    }

    fun getMainActivityComponent(): FirstScreenComponent {
        if (firstScreenComponent == null) {
            firstScreenComponent = appComponent.provideMainActivityBuilder().build()
        }
        return firstScreenComponent!!
    }

    fun releaseMainActivityComponent() {
        firstScreenComponent = null
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}