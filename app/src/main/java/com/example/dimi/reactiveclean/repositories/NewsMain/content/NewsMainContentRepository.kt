package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsMainContentRepository {

    fun getAllContent(): Flowable<List<Content>>

    fun loadMoreContent(page: Int): Single<ContentPages>

    fun deleteAndFetchContent(): Single<ContentPages>

    //fun getSpecificContentStream(params: String): Single<List<Content>>
}