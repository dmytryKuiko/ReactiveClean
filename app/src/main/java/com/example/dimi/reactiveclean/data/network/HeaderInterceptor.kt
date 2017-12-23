package com.example.dimi.reactiveclean.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    private val API_KEY = "api-key"

    private val API_KEY_VALUE = "d474bd2d-2865-4f91-bd31-018ee20422d6"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val newUrl = request.url().newBuilder()
                .addQueryParameter(API_KEY, API_KEY_VALUE)
                .build()

        val requestBuilder = request.newBuilder().url(newUrl)

        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("Authorization", "fooToken")
        }
        return chain.proceed(requestBuilder.build())
    }
}