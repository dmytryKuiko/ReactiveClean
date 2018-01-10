package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.NewsMain.content.ContentStore
import com.example.dimi.reactiveclean.data.NewsMain.content.ContentStoreImpl
import com.example.dimi.reactiveclean.data.NewsMain.section.SectionStore
import com.example.dimi.reactiveclean.data.NewsMain.section.SectionStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.components.SectionChosenComponent
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractorImpl
import com.example.dimi.reactiveclean.domain.NewsMain.content.ContentInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.content.ContentInterractorImpl
import com.example.dimi.reactiveclean.domain.NewsMain.section.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.section.NewsMainSectionsInterractorImpl
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigator
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigatorImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenterImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.ContentPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.ContentPresenterImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section.SectionPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section.SectionPresenterImpl
import com.example.dimi.reactiveclean.repositories.NewsMain.content.ContentRepository
import com.example.dimi.reactiveclean.repositories.NewsMain.content.ContentRepositoryImpl
import com.example.dimi.reactiveclean.repositories.NewsMain.section.SectionRepository
import com.example.dimi.reactiveclean.repositories.NewsMain.section.SectionRepositoryImpl
import com.example.dimi.reactiveclean.utils.paginator.PaginatorDB
import com.example.dimi.reactiveclean.utils.paginator.PaginatorDBImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(subcomponents = [SectionChosenComponent::class])
abstract class NewsMainModule {

    @Binds
    @ActivityScope
    internal abstract fun bindMainPresenter(presenter: NewsMainPresenterImpl): NewsMainPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindContentPresenter(presenter: ContentPresenterImpl): ContentPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsPresenter(presenter: SectionPresenterImpl): SectionPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindNewsMainNavigator(navigator: NewsMainNavigatorImpl): NewsMainNavigator

    @Binds
    @ActivityScope
    internal abstract fun bindMainInterractor(interractor: NewsMainInterractorImpl): NewsMainInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindContentInterractor(interractor: ContentInterractorImpl): ContentInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsInterractor(interractor: NewsMainSectionsInterractorImpl):
            NewsMainSectionsInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindContentRepository(repository: ContentRepositoryImpl): ContentRepository

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsRepository(repository: SectionRepositoryImpl): SectionRepository

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsStore(store: SectionStoreImpl): SectionStore

    @Binds
    @ActivityScope
    internal abstract fun bindContentStore(store: ContentStoreImpl): ContentStore

    @Binds
    @ActivityScope
    @Named(DiConstants.NEWS_MAIN_CONTENT_PAGINATOR)
    internal abstract fun bindContentPaginator(pag: PaginatorDBImpl<ContentDisplayable>): PaginatorDB<ContentDisplayable>

    @Module
    companion object {}
}