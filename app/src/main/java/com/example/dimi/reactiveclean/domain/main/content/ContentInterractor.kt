package com.example.dimi.reactiveclean.domain.main.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ContentInterractor {

    fun getContentStream(): Flowable<List<Content>>

    fun loadNews(): Completable

    fun searchContent(text: Observable<String>): Observable<ContentPages>

    fun loadNextContentPage(page: Int): Completable
}