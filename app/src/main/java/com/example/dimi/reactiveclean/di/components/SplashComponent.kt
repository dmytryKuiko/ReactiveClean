package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.modules.SplashModule
import com.example.dimi.reactiveclean.di.scopes.SplashScope
import com.example.dimi.reactiveclean.presentation.Splash.view.SplashActivity
import dagger.Subcomponent

@SplashScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent : BaseComponent<SplashActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): SplashComponent
    }
}