package com.example.dimi.reactiveclean.data.network

import com.example.dimi.reactiveclean.models.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceNewsApi {

    @GET("top-headlines")
    @Headers("No-Authentication: true")
    fun getHeadlinesFor(@Query("sources") sources: String): Single<List<ArticleResponse>>
}