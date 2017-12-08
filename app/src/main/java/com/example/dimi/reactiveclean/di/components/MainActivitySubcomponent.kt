package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity
import com.example.dimi.reactiveclean.di.modules.MainActivityAbstractModule
import com.example.dimi.reactiveclean.di.modules.MainActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [(MainActivityModule::class), (MainActivityAbstractModule::class)])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}