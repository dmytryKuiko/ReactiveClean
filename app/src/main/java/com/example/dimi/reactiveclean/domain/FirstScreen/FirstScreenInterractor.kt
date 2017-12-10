package com.example.dimi.reactiveclean.domain.FirstScreen

import io.reactivex.Completable
import io.reactivex.Flowable

interface FirstScreenInterractor<Params, Value> {

    fun getArticlesStream(params: Params?): Flowable<Value>

    fun refreshArticles(): Completable
}