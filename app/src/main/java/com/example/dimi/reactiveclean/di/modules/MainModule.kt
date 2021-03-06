package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.main.content.ContentStore
import com.example.dimi.reactiveclean.data.main.content.ContentStoreImpl
import com.example.dimi.reactiveclean.data.main.section.SectionStore
import com.example.dimi.reactiveclean.data.main.section.SectionStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigatorImpl
import com.example.dimi.reactiveclean.presentation.main.presenter.content.ContentPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.content.ContentPresenterImpl
import com.example.dimi.reactiveclean.presentation.main.presenter.section.SectionPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.section.SectionPresenterImpl
import com.example.dimi.reactiveclean.data.main.content.ContentRepository
import com.example.dimi.reactiveclean.data.main.content.ContentRepositoryImpl
import com.example.dimi.reactiveclean.data.main.section.SectionRepository
import com.example.dimi.reactiveclean.data.main.section.SectionRepositoryImpl
import com.example.dimi.reactiveclean.di.components.MainComponent
import com.example.dimi.reactiveclean.domain.main.content.ContentInteractor
import com.example.dimi.reactiveclean.domain.main.content.ContentInteractorImpl
import com.example.dimi.reactiveclean.domain.main.section.SectionInteractor
import com.example.dimi.reactiveclean.domain.main.section.SectionInteractorImpl
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDB
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDBImpl
import com.example.dimi.reactiveclean.presentation.main.presenter.*
import com.example.dimi.reactiveclean.presentation.main.presenter.drawer.DrawerPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.drawer.DrawerPresenterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
 * Module for MainComponent
 * @see MainComponent
 */
@Module
abstract class MainModule {

    @ActivityScope
    @Binds
    internal abstract fun bindMainPresenter(presenter: MainPresenterImpl): MainPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindContentPresenter(presenter: ContentPresenterImpl): ContentPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindSectionsPresenter(presenter: SectionPresenterImpl): SectionPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindNewsMainNavigator(navigator: NewsMainNavigatorImpl): NewsMainNavigator

    @ActivityScope
    @Binds
    internal abstract fun bindContentinteractor(interactor: ContentInteractorImpl): ContentInteractor

    @ActivityScope
    @Binds
    internal abstract fun bindSectionsinteractor(
        interactor: SectionInteractorImpl
    ): SectionInteractor

    @ActivityScope
    @Binds
    internal abstract fun bindContentRepository(repository: ContentRepositoryImpl): ContentRepository

    @ActivityScope
    @Binds
    internal abstract fun bindSectionsRepository(repository: SectionRepositoryImpl): SectionRepository

    @ActivityScope
    @Binds
    internal abstract fun bindSectionsStore(store: SectionStoreImpl): SectionStore

    @ActivityScope
    @Binds
    internal abstract fun bindContentStore(store: ContentStoreImpl): ContentStore

    @Named(DiConstants.NEWS_MAIN_CONTENT_PAGINATOR)
    @ActivityScope
    @Binds
    internal abstract fun bindContentPaginator(pag: PaginatorDBImpl<ContentDisplayable>): PaginatorDB<ContentDisplayable>

    @ActivityScope
    @Binds
    internal abstract fun bindMenuController(menuController: MenuControllerImpl): MenuController

    @ActivityScope
    @Binds
    internal abstract fun bindDrawerPresenter(presenter: DrawerPresenterImpl): DrawerPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindMainFragmentPresenter(presenter: MainFragmentPresenterImpl): MainFragmentPresenter

    @Module
    companion object {}
}