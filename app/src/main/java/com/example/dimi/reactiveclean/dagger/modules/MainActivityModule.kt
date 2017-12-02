package com.example.dimi.reactiveclean.dagger.modules

import com.example.dimi.reactiveclean.dagger.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideString() : String = "some string"
}