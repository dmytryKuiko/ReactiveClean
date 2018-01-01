package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.Splash.SplashReactiveStore
import com.example.dimi.reactiveclean.data.Splash.SplashReactiveStoreImpl
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractor
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractorImpl
import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigator
import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigatorImpl
import com.example.dimi.reactiveclean.presentation.Splash.presenter.SplashPresenter
import com.example.dimi.reactiveclean.presentation.Splash.presenter.SplashPresenterImpl
import com.example.dimi.reactiveclean.repositories.Splash.SplashRepository
import com.example.dimi.reactiveclean.repositories.Splash.SplashRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @Binds
    @ActivityScope
    internal abstract fun bindReactiveStore(store: SplashReactiveStoreImpl): SplashReactiveStore

    @Binds
    @ActivityScope
    internal abstract fun bindRepository(repository: SplashRepositoryImpl): SplashRepository

    @Binds
    @ActivityScope
    internal abstract fun bindInterractor(interractor: SplashInterractorImpl): SplashInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindPresenter(presenter: SplashPresenterImpl): SplashPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindNavigator(navigator: SplashNavigatorImpl): SplashNavigator

}