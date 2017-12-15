package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.Main.MainDataMapper
import com.example.dimi.reactiveclean.data.Main.MainReactiveStore
import com.example.dimi.reactiveclean.data.Main.MainReactiveStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.MainScope
import com.example.dimi.reactiveclean.domain.Main.MainInterractor
import com.example.dimi.reactiveclean.domain.Main.MainInterractorImpl
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleResponse
import com.example.dimi.reactiveclean.presentation.Main.presenter.*
import com.example.dimi.reactiveclean.repositories.Main.MainRepository
import com.example.dimi.reactiveclean.repositories.Main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import io.reactivex.functions.Function
import javax.inject.Named

@Module
abstract class MainModule {

    @Binds
    @MainScope
    internal abstract fun bindRepository(repo: MainRepositoryImpl): MainRepository

    @Binds
    @MainScope
    internal abstract fun bindStore(store: MainReactiveStoreImpl): MainReactiveStore<Long, Article>

    @Binds
    @MainScope
    internal abstract fun bindInterractor(interractor: MainInterractorImpl):
            MainInterractor<Nothing, List<Article>>

    @Binds
    @MainScope
    @Named(DiConstants.MAIN_DATA_MAPPER)
    internal abstract fun bindDataMapper(mapper: MainDataMapper): Function<ArticleResponse, Article>

    @Binds
    @MainScope
    internal abstract fun bindFirstScreenPresenter(presenter: MainPresenterImpl): MainPresenter

    /**
     * Dagger generate java.util.List instead of kotlin.collection.List
     */
//    @Binds
//    @ActivityScope
//    @Named("functionArticleDesplayableItemMapped")
//    internal abstract fun provideFunctionArticleDisplayableItemMapper
//            (mapperArticleDisplayableItem: MainDomainMapper):
//            Function<@JvmSuppressWildcards List<Article>,@JvmSuppressWildcards List<ArticleDisplayableItem>>
}