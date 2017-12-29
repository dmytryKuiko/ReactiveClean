package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentRecyclerData
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsMainContentInterractor {

    fun getContentStream(): Flowable<List<Content>>

    fun loadNews(): Single<ContentPages>

    //fun getSpecificContentStream(params: String): Single<List<Content>>

    fun loadMoreContent(lastVisibleAndAllItems: Observable<ContentRecyclerData>, relay: PublishRelay<ContentPages>): Observable<ContentPages>
}