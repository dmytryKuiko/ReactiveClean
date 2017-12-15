package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.utils.NavigatorBuilder
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers.ExtendedRouter
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers.ExtendedRouterImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone = Cicerone.create(ExtendedRouterImpl())

    @Provides
    @Singleton
    fun provideNavigator(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideRouter(): ExtendedRouter = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorBuilder(): NavigatorBuilder = NavigatorBuilder()
}