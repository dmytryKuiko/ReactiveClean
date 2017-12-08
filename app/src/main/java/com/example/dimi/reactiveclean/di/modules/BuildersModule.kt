package com.example.dimi.reactiveclean.di.modules

import android.app.Activity
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity
import com.example.dimi.reactiveclean.di.components.MainActivitySubcomponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder) : AndroidInjector.Factory<out Activity>

}