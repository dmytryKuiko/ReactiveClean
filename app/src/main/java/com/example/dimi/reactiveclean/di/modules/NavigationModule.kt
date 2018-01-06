package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.navigation.extended.ExtendedRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone = Cicerone.create(ExtendedRouter())

    @Provides
    @Singleton
    fun provideNavigator(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideExtendedRouter(): ExtendedRouter = cicerone.router
}