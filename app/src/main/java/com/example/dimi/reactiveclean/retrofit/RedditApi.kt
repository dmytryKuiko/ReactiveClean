package com.example.dimi.reactiveclean.retrofit

import com.example.dimi.reactiveclean.models.Data
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RedditApi {
    @GET("/top.json")
    @Headers("No-Authentication: true")
    fun getTop(@Query("limit") limit: String): Single<Data>
}