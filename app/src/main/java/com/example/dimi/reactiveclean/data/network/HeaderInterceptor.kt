package com.example.dimi.reactiveclean.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val newUrl = request.url().newBuilder()
                .addQueryParameter("apiKey", "5d46a2c6349c4b878e2d0d873c57ef37")
                .build()

        val requestBuilder = request.newBuilder().url(newUrl)

        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("Authorization", "fooToken")
        }
        return chain.proceed(requestBuilder.build())
    }
}