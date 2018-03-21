package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.splash.SplashReactiveStore
import com.example.dimi.reactiveclean.data.splash.SplashReactiveStoreImpl
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.di.components.SplashComponent
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigatorImpl
import com.example.dimi.reactiveclean.presentation.splash.presenter.SplashPresenter
import com.example.dimi.reactiveclean.presentation.splash.presenter.SplashPresenterImpl
import com.example.dimi.reactiveclean.data.splash.SplashRepository
import com.example.dimi.reactiveclean.data.splash.SplashRepositoryImpl
import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import com.example.dimi.reactiveclean.domain.splash.SplashInteractorImpl
import dagger.Binds
import dagger.Module

/**
 * Module for SplashComponent
 * @see SplashComponent
 */
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
    internal abstract fun bindinteractor(interactor: SplashInteractorImpl): SplashInteractor

    @ActivityScope
    @Binds
    internal abstract fun bindPresenter(presenter: SplashPresenterImpl): SplashPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindNavigator(navigator: SplashNavigatorImpl): SplashNavigator

}