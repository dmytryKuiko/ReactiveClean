package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.Splash.SplashReactiveStore
import com.example.dimi.reactiveclean.data.Splash.SplashReactiveStoreImpl
import com.example.dimi.reactiveclean.di.scopes.SplashScope
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractor
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractorImpl
import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigator
import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigatorImpl
import com.example.dimi.reactiveclean.presentation.Splash.view_model.SplashPresenter
import com.example.dimi.reactiveclean.presentation.Splash.view_model.SplashPresenterImpl
import com.example.dimi.reactiveclean.repositories.Splash.SplashRepository
import com.example.dimi.reactiveclean.repositories.Splash.SplashRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @Binds
    @SplashScope
    internal abstract fun bindReactiveStore(store: SplashReactiveStoreImpl): SplashReactiveStore

    @Binds
    @SplashScope
    internal abstract fun bindRepository(repository: SplashRepositoryImpl): SplashRepository

    @Binds
    @SplashScope
    internal abstract fun bindInterractor(interractor: SplashInterractorImpl): SplashInterractor

    @Binds
    @SplashScope
    internal abstract fun bindPresenter(presenter: SplashPresenterImpl): SplashPresenter

    @Binds
    @SplashScope
    internal abstract fun bindNavigator(navigator: SplashNavigatorImpl): SplashNavigator

}