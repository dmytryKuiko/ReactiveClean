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
    internal abstract fun bindPresenter(presenter: SearchPresenterImpl): SearchPresenter

    @CustomScope
    @Binds
    internal abstract fun bindInterractor(interractor: SearchInterractorImpl): SearchInterractor

    @CustomScope
    @Binds
    internal abstract fun bindRepository(repo: SearchRepositoryImpl): SearchRepository

    @CustomScope
    @Binds
    internal abstract fun bindStore(store: SearchStoreImpl): SearchStore
}