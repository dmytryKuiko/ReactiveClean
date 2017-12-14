package com.example.dimi.reactiveclean

import android.app.Application
import com.example.dimi.reactiveclean.utils.ComponentManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentManager.initAppComponent(this)
    }
}