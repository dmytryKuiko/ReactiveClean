package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.main.content.ContentStore
import com.example.dimi.reactiveclean.data.main.content.ContentStoreImpl
import com.example.dimi.reactiveclean.data.main.section.SectionStore
import com.example.dimi.reactiveclean.data.main.section.SectionStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.components.SectionChosenComponent
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.domain.main.NewsMainInterractor
import com.example.dimi.reactiveclean.domain.main.NewsMainInterractorImpl
import com.example.dimi.reactiveclean.domain.main.content.ContentInterractor
import com.example.dimi.reactiveclean.domain.main.content.ContentInterractorImpl
import com.example.dimi.reactiveclean.domain.main.section.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.domain.main.section.NewsMainSectionsInterractorImpl
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
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDB
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDBImpl
import com.example.dimi.reactiveclean.presentation.main.presenter.*
import com.example.dimi.reactiveclean.presentation.main.presenter.drawer.DrawerPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.drawer.DrawerPresenterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(subcomponents = [SectionChosenComponent::class])
abstract class NewsMainModule {

    @ActivityScope
    @Binds
    internal abstract fun bindMainPresenter(presenter: NewsMainPresenterImpl): NewsMainPresenter

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
    internal abstract fun bindMainInterractor(interractor: NewsMainInterractorImpl): NewsMainInterractor

    @ActivityScope
    @Binds
    internal abstract fun bindContentInterractor(interractor: ContentInterractorImpl): ContentInterractor

    @ActivityScope
    @Binds
    internal abstract fun bindSectionsInterractor(
        interractor: NewsMainSectionsInterractorImpl
    ): NewsMainSectionsInterractor

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