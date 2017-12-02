package com.example.dimi.reactiveclean.dagger.components

import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.dagger.modules.AppModule
import com.example.dimi.reactiveclean.dagger.modules.BuildersModule
import com.example.dimi.reactiveclean.dagger.modules.NetworkAbstractModule
import com.example.dimi.reactiveclean.dagger.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [(AndroidSupportInjectionModule::class),(BuildersModule::class),
    (AppModule::class), (NetworkModule::class), (NetworkAbstractModule::class)])

@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        @Singleton
        fun application(app: App): Builder

        @BindsInstance
        @Singleton
        fun baseUrlRetrofit(url: String): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}