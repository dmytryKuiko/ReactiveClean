package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NavigationModule::class, NetworkModule::class, NetworkAbstractModule::class])

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

    fun splashComponentBuilder(): SplashComponent.Builder

    fun mainComponentBuilder(): MainComponent.Builder

    fun tutorialComponentBuilder(): TutorialComponent.Builder

}