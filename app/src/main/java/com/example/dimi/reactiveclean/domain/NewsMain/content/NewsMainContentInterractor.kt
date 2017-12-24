package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsMainContentInterractor {

    fun getContentStream(): Flowable<List<Content>>

    fun getSpecificContentStream(params: String): Single<List<Content>>
}