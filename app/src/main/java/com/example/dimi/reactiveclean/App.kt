package com.example.dimi.reactiveclean

import android.app.Application
import com.example.dimi.reactiveclean.utils.ComponentManager
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        ComponentManager.initAppComponent(this)
    }
}