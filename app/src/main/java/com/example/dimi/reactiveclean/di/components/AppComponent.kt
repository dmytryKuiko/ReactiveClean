package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class),
    (NetworkModule::class), (NetworkAbstractModule::class)])

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

    fun provideMainActivityBuilder(): FirstScreenComponent.Builder

}