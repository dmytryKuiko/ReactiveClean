package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenDataMapper
import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStore
import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStoreImpl
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractor
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractorImpl
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleResponse
import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepository
import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import io.reactivex.functions.Function
import javax.inject.Named

@Module
abstract class MainActivityAbstractModule {

    @Binds
//    @ActivityScope
    internal abstract fun provideFirstScreenRepository(articleRepositoryImpl: FirstScreenRepositoryImpl): FirstScreenRepository

    @Binds
//    @ActivityScope
    internal abstract fun provideFirstScreenStore(reactiveStoreImpl: FirstScreenReactiveStoreImpl): FirstScreenReactiveStore<Long, Article>

    @Binds
//    @ActivityScope
    internal abstract fun provideFirstScreenInterractor(interractorArticleListImpl: FirstScreenInterractorImpl):
            FirstScreenInterractor<Nothing, List<Article>>

    @Binds
//    @ActivityScope
    @Named("firstScreenDataMapper")
    internal abstract fun provideFirstScreenDataMapper(firstScreenDataMapper: FirstScreenDataMapper): Function<ArticleResponse, Article>

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