package com.example.dimi.reactiveclean.dagger.components

import com.example.dimi.reactiveclean.MainActivity
import com.example.dimi.reactiveclean.dagger.modules.MainActivityModule
import com.example.dimi.reactiveclean.dagger.scopes.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [(MainActivityModule::class)])
@ActivityScope
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}