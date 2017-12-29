package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentManager
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStoreImpl
import com.example.dimi.reactiveclean.data.NewsMain.sections.NewsMainSectionsStore
import com.example.dimi.reactiveclean.data.NewsMain.sections.NewsMainSectionsStoreImpl
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractorImpl
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractorImpl
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsInterractorImpl
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigator
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigatorImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenterImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.NewsMainContentPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.NewsMainContentPresenterImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections.NewsMainSectionsPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections.NewsMainSectionsPresenterImpl
import com.example.dimi.reactiveclean.repositories.NewsMain.content.NewsMainContentRepository
import com.example.dimi.reactiveclean.repositories.NewsMain.content.NewsMainContentRepositoryImpl
import com.example.dimi.reactiveclean.repositories.NewsMain.sections.NewsMainSectionsRepository
import com.example.dimi.reactiveclean.repositories.NewsMain.sections.NewsMainSectionsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NewsMainModule {

    @Binds
    @ActivityScope
    internal abstract fun bindMainPresenter(presenter: NewsMainPresenterImpl): NewsMainPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindContentPresenter(presenter: NewsMainContentPresenterImpl): NewsMainContentPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsPresenter(presenter: NewsMainSectionsPresenterImpl): NewsMainSectionsPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindNewsMainNavigator(navigator: NewsMainNavigatorImpl): NewsMainNavigator

    @Binds
    @ActivityScope
    internal abstract fun bindMainInterractor(interractor: NewsMainInterractorImpl): NewsMainInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindContentInterractor(interractor: NewsMainContentInterractorImpl): NewsMainContentInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsInterractor(interractor: NewsMainSectionsInterractorImpl):
            NewsMainSectionsInterractor

    @Binds
    @ActivityScope
    internal abstract fun bindContentRepository(repository: NewsMainContentRepositoryImpl): NewsMainContentRepository

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsRepository(repository: NewsMainSectionsRepositoryImpl): NewsMainSectionsRepository

    @Binds
    @ActivityScope
    internal abstract fun bindSectionsStore(store: NewsMainSectionsStoreImpl): NewsMainSectionsStore

    @Binds
    @ActivityScope
    internal abstract fun bindContentStore(store: NewsMainContentStoreImpl): NewsMainContentStore

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideContentPages(): ContentPages = ContentPages()
    }
}