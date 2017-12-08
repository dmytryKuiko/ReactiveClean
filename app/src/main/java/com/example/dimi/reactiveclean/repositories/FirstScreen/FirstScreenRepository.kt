package com.example.dimi.reactiveclean.repositories.FirstScreen

import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface FirstScreenRepository {

    fun getAllArticles(): Flowable<List<Article>>

    fun fetchArticles(): Completable
}