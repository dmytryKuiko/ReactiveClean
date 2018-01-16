package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.main.search.SearchRepository
import com.example.dimi.reactiveclean.data.main.search.SearchRepositoryImpl
import com.example.dimi.reactiveclean.data.main.search.SearchStore
import com.example.dimi.reactiveclean.data.main.search.SearchStoreImpl
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.domain.main.search.SearchInterractor
import com.example.dimi.reactiveclean.domain.main.search.SearchInterractorImpl
import com.example.dimi.reactiveclean.presentation.main.presenter.search.SearchPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.search.SearchPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SearchModule {

    @CustomScope
    @Binds
    abstract internal fun bindPresenter(presenter: SearchPresenterImpl): SearchPresenter

    @CustomScope
    @Binds
    abstract internal fun bindInterractor(interractor: SearchInterractorImpl): SearchInterractor

    @CustomScope
    @Binds
    abstract internal fun bindRepository(repo: SearchRepositoryImpl): SearchRepository

    @CustomScope
    @Binds
    abstract internal fun bindStore(store: SearchStoreImpl): SearchStore
}