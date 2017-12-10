package com.example.dimi.reactiveclean.di.modules

import android.arch.lifecycle.ViewModel
import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenDataMapper
import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStore
import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStoreImpl
import com.example.dimi.reactiveclean.di.scopes.FirstScreen
import com.example.dimi.reactiveclean.di.scopes.ViewModelKey
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractor
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractorImpl
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import com.example.dimi.reactiveclean.models.ArticleResponse
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.*
import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepository
import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.functions.Function
import javax.inject.Named

@Module
abstract class FirstScreenAbstractModule {

    @Binds
    @FirstScreen
    internal abstract fun provideRepository(articleRepositoryImpl: FirstScreenRepositoryImpl): FirstScreenRepository

    @Binds
    @FirstScreen
    internal abstract fun provideStore(reactiveStoreImpl: FirstScreenReactiveStoreImpl): FirstScreenReactiveStore<Long, Article>

    @Binds
    @FirstScreen
    internal abstract fun provideInterractor(interractorArticleListImpl: FirstScreenInterractorImpl):
            FirstScreenInterractor<Nothing, List<Article>>

    @Binds
    @FirstScreen
    @Named("firstScreenDataMapper")
    internal abstract fun providenDataMapper(firstScreenDataMapper: FirstScreenDataMapper): Function<ArticleResponse, Article>

    @Binds
    @FirstScreen
    internal abstract fun providePresenter(presenterImpl: FirstScreenPresenterImpl): FirstScreenPresenter

    @Binds
    @IntoMap
    @FirstScreen
    @ViewModelKey(FirstScreenViewModelImpl::class)
    abstract fun bindMainViewModel(firstScreenViewModelImpl: FirstScreenViewModelImpl): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideDisplayableModel() = FirstScreenPresenterCache()
    }

    /**
     * Dagger generate java.util.List instead of kotlin.collection.List
     */
//    @Binds
//    @ActivityScope
//    @Named("functionArticleDesplayableItemMapped")
//    internal abstract fun provideFunctionArticleDisplayableItemMapper
//            (mapperArticleDisplayableItem: FirstScreenDomainMapper):
//            Function<@JvmSuppressWildcards List<Article>,@JvmSuppressWildcards List<ArticleDisplayableItem>>
}