package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentRecyclerData
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsMainContentInterractor {

    fun getContentStream(): Flowable<List<Content>>

    fun loadNews(): Single<ContentPages>

    fun loadMoreContent(rxBinding: Observable<ContentRecyclerData>, relay: BehaviorRelay<ContentPages>): Observable<ContentPages>

    fun searchContent(text: Observable<String>): Observable<ContentPages>
}