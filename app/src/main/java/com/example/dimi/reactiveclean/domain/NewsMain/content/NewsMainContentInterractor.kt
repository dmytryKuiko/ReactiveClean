package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsMainContentInterractor {

    fun getContentStream(): Flowable<List<Content>>

    fun loadNews(): Completable

    fun searchContent(text: Observable<String>): Observable<ContentPages>

    fun loadNextContentPage(page: Int): Completable
}