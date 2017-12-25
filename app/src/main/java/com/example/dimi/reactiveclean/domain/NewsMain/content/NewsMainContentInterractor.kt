package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.models.content.Content
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsMainContentInterractor {

    fun getContentStream(): Flowable<List<BaseItem>>

    fun loadNews(): Completable

    fun getSpecificContentStream(params: String): Single<List<Content>>

    fun loadMoreContent(listener: Observable<Int>): Completable
}