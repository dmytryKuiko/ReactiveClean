package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.modules.MainModule
import com.example.dimi.reactiveclean.di.scopes.MainScope
import com.example.dimi.reactiveclean.presentation.Main.view.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent : BaseComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}