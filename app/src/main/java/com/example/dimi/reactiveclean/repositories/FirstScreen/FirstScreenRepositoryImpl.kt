package com.example.dimi.reactiveclean.repositories.FirstScreen

import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Named

//@ActivityScope
class FirstScreenRepositoryImpl
@Inject
constructor
(private val reactiveStore: FirstScreenReactiveStore<Long, Article>,
 private val serviceNewsApi: ServiceNewsApi,
// private val mapperArticle: FirstScreenDataMapper) : FirstScreenRepository {
 @Named("firstScreenDataMapper") private val mapperArticle: Function<ArticleResponse, Article>) : FirstScreenRepository {

    init {
        val a = 3
        val b = 2
    }
    override fun getAllArticles(): Flowable<List<Article>> = reactiveStore.getAll()

    override fun fetchArticles(): Completable = serviceNewsApi.getHeadlinesFor("techcrunch")
            .flatMapObservable { list -> Observable.fromIterable(list) }
            .map(mapperArticle)
            .toList()
            .doOnSuccess(reactiveStore::storeAll)
            .toCompletable()
}