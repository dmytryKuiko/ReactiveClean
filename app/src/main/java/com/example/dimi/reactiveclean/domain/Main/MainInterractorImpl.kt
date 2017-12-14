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
constructor(private val mainRepository: MainRepository) :
        MainInterractor<Nothing, List<Article>> {

    init {
        var a = 2
        a++
        val b = 2
    }

    override fun getArticlesStream(params: Nothing?): Flowable<kotlin.collections.List<Article>> =
            mainRepository.getAllArticles()
                    .flatMapSingle(this::fetchWhenNoneAndThenDrafts)
                    .filter { t: List<Article> -> t.isNotEmpty() }

    override fun refreshArticles(): Completable =
            mainRepository.deleteAndFetchArticles().toCompletable()

    private fun fetchWhenNoneAndThenDrafts(listArticles: List<Article>): Single<List<Article>> =
            fetchWhenNone(listArticles).andThen(just(listArticles)).observeOn(AndroidSchedulers.mainThread())

    private fun fetchWhenNone(listArticles: List<Article>): Completable =
            when (listArticles.isEmpty()) {
                true -> mainRepository.fetchArticles()
                else -> Completable.complete()
            }
}