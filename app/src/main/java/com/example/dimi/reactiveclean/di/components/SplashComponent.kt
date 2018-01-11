package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.BaseComponent
import com.example.dimi.reactiveclean.di.modules.SplashModule
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.presentation.splash.view.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent : BaseComponent<SplashActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): SplashComponent
    }
}