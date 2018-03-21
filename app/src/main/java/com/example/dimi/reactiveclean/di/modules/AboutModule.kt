package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.di.components.AboutComponent
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.presentation.main.presenter.about.AboutPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.about.AboutPresenterImpl
import dagger.Binds
import dagger.Module

/**
 * Modules for AboutComponent
 * @see AboutComponent
 */
@Module
abstract class AboutModule {

    @CustomScope
    @Binds
    internal abstract fun bindPresenter(presenter: AboutPresenterImpl): AboutPresenter
}