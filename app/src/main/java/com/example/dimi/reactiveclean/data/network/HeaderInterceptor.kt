package com.example.dimi.reactiveclean.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrlBuilder = request.url().newBuilder()

        if (request.header(DEFAULT_PAGE_SIZE) != null) {
            httpUrlBuilder.addQueryParameter(PAGE_SIZE, DEFAULT_PAGE_SIZE_VALUE)
        }

        val newUrl = httpUrlBuilder.addQueryParameter(API_KEY, API_KEY_VALUE).build()
        val requestBuilder = request.newBuilder().url(newUrl)

        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("Authorization", "fooToken")
        }
        return chain.proceed(requestBuilder.build())
    }

    companion object {

        private val API_KEY = "api-key"

        private val API_KEY_VALUE = "d474bd2d-2865-4f91-bd31-018ee20422d6"

        private val PAGE_SIZE = "page-size"

        const val DEFAULT_PAGE_SIZE = "DefaultPageSize"

        private val DEFAULT_PAGE_SIZE_VALUE = "25"
    }
}