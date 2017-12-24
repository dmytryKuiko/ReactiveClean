package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsMainContentRepository {

    fun getAllContent(): Flowable<List<Content>>

    fun fetchContent(): Completable

    fun deleteAndFetchContent(): Completable

    fun getSpecificContentStream(params: String): Single<List<Content>>
}