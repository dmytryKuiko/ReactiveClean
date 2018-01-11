package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ContentRepository {

    fun getAllContent(): Flowable<List<Content>>

    fun deleteAndFetchContent(): Completable

    fun searchContent(text: String): Single<ContentPages>

    fun loadNextContentPage(page: Int): Completable

}