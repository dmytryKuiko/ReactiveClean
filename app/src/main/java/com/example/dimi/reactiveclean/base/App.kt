package com.example.dimi.reactiveclean.base

import android.app.Activity
import android.app.Application
import com.example.dimi.reactiveclean.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)
                .baseUrlRetrofit("https://newsapi.org/v2/")
                .build()
                .inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}