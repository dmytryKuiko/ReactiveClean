package com.example.dimi.reactiveclean.domain.Main

import io.reactivex.Completable
import io.reactivex.Flowable

interface MainInterractor<Params, Value> {

    fun getArticlesStream(params: Params?): Flowable<Value>

    fun refreshArticles(): Completable
}