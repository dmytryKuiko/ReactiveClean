package com.example.dimi.reactiveclean.data.network

import com.example.dimi.reactiveclean.data.network.HeaderInterceptor.Companion.DEFAULT_PAGE_SIZE
import com.example.dimi.reactiveclean.models.ArticleResponse
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.sections.SectionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceNewsApi {

    @GET("top-headlines")
    @Headers("No-Authentication: true")
    fun getHeadlinesFor(@Query("sources") sources: String): Single<List<ArticleResponse>>

    @GET("sections")
    @Headers("No-Authentication: true")
    fun getAllSections(): Single<SectionResponse>

    @GET("search")
    @Headers("$DEFAULT_PAGE_SIZE: true")
    fun getAllContent(): Single<ContentResponse>

    @GET("search")
    @Headers("$DEFAULT_PAGE_SIZE: true")
    fun getNextContent(@Query("page") page: Int): Single<ContentResponse>

    @GET("search")
    @Headers("No-Authentication: true")
    fun getSpecificContent(@Query("q") params: String): Single<ContentResponse>
}