package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.splash.SplashReactiveStore
import com.example.dimi.reactiveclean.data.splash.SplashReactiveStoreImpl
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.domain.splash.SplashInterractor
import com.example.dimi.reactiveclean.domain.splash.SplashInterractorImpl
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigatorImpl
import com.example.dimi.reactiveclean.presentation.splash.presenter.SplashPresenter
import com.example.dimi.reactiveclean.presentation.splash.presenter.SplashPresenterImpl
import com.example.dimi.reactiveclean.data.splash.SplashRepository
import com.example.dimi.reactiveclean.data.splash.SplashRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @ActivityScope
    @Binds
    internal abstract fun bindReactiveStore(store: SplashReactiveStoreImpl): SplashReactiveStore

    @ActivityScope
    @Binds
    internal abstract fun bindRepository(repository: SplashRepositoryImpl): SplashRepository

    @ActivityScope
    @Binds
    internal abstract fun bindInterractor(interractor: SplashInterractorImpl): SplashInterractor

    @ActivityScope
    @Binds
    internal abstract fun bindPresenter(presenter: SplashPresenterImpl): SplashPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindNavigator(navigator: SplashNavigatorImpl): SplashNavigator

}