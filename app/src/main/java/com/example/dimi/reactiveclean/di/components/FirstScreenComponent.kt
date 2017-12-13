package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.modules.FirstScreenModule
import com.example.dimi.reactiveclean.di.scopes.FirstScreen
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity
import dagger.Subcomponent

@FirstScreen
@Subcomponent(modules = [(FirstScreenModule::class)])
interface FirstScreenComponent : BaseComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FirstScreenComponent
    }
}