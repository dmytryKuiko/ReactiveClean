package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.di.components.AppComponent
import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

/**
 * Module for AppComponent
 * @see AppComponent
 */
@Module
class NavigationModule {

    private val cicerone = Cicerone.create(ExtendedRouter())

    @Singleton
    @Provides
    fun provideNavigator(): NavigatorHolder = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun provideExtendedRouter(): ExtendedRouter = cicerone.router
}