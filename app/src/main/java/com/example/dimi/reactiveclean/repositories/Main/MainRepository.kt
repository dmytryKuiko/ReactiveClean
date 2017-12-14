package com.example.dimi.reactiveclean.repositories.Main

import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MainRepository {

    fun getAllArticles(): Flowable<List<Article>>

    fun fetchArticles(): Completable

    fun deleteAndFetchArticles(): Single<List<Article>>
}