package com.example.dimi.reactiveclean.domain.FirstScreen

import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepository
import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

class FirstScreenInterractorImpl
@Inject
constructor(private val firstScreenRepository: FirstScreenRepository) :
        FirstScreenInterractor<Nothing, List<Article>> {

    init {
        val a = 3
        val b = 2
    }


    override fun getArticlesStream(params: Nothing?): Flowable<kotlin.collections.List<Article>> =
            firstScreenRepository.getAllArticles()
                    .flatMapSingle (this::fetchWhenNoneAndThenDrafts )
                    .filter { t: List<Article> -> t.isNotEmpty() }

    private fun fetchWhenNoneAndThenDrafts(listArticles: List<Article>): Single<List<Article>> =
            fetchWhenNone(listArticles).andThen(just(listArticles))

    private fun fetchWhenNone(listArticles: List<Article>): Completable =
            when(listArticles.isEmpty()) {
                true -> firstScreenRepository.fetchArticles()
                else -> Completable.complete()
            }
}