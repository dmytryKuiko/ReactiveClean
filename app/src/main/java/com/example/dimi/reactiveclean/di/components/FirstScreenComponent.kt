package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.modules.FirstScreenAbstractModule
import com.example.dimi.reactiveclean.di.modules.FirstScreenModule
import com.example.dimi.reactiveclean.di.scopes.FirstScreen
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity
import dagger.Subcomponent

@FirstScreen
@Subcomponent(modules = [(FirstScreenModule::class), (FirstScreenAbstractModule::class)])
interface FirstScreenComponent : BaseComponent<MainActivity> {

//    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun mainActivityModule(module: FirstScreenModule): Builder

        fun build(): FirstScreenComponent
    }
}