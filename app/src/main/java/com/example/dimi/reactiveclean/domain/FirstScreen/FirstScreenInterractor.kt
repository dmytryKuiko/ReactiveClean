package com.example.dimi.reactiveclean.domain.FirstScreen

import io.reactivex.Flowable

interface FirstScreenInterractor<Params, Value> {

    fun getArticlesStream(params: Params?): Flowable<Value>
}