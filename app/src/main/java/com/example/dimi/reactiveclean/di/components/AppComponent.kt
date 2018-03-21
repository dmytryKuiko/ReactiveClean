package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.modules.*
import com.example.dimi.reactiveclean.utils.ComponentManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Main Component
 * @see ComponentManager
 * provides builders for Splash, Tutorial and Main activities
 */
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

    fun tutorialComponentBuilder(): TutorialComponent.Builder

    fun mainComponentBuilder(): MainComponent.Builder

}