package com.example.dimi.reactiveclean.domain.Main

import com.example.dimi.reactiveclean.repositories.Main.MainRepository
import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.Single.just
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainInterractorImpl
@Inject
constructor(private val repository: MainRepository) :
        MainInterractor<Nothing, List<Article>> {

    override fun getArticlesStream(params: Nothing?): Flowable<kotlin.collections.List<Article>> =
            repository.getAllArticles()
                    .flatMapSingle(this::fetchWhenNoneAndThenDrafts)
                    .filter { t: List<Article> -> t.isNotEmpty() }

    override fun refreshArticles(): Completable =
            repository.deleteAndFetchArticles()
                    .toCompletable()

    private fun fetchWhenNoneAndThenDrafts(listArticles: List<Article>): Single<List<Article>> =
            fetchWhenNone(listArticles)
                    .andThen(just(listArticles))
                    .observeOn(AndroidSchedulers.mainThread())

    private fun fetchWhenNone(listArticles: List<Article>): Completable =
            when (listArticles.isEmpty()) {
                true -> repository.fetchArticles()
                else -> Completable.complete()
            }
}