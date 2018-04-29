package com.example.dimi.reactiveclean.data.network

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor for Network requests
 */
class HeaderInterceptor
@Inject constructor() : Interceptor {

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

        const val API_KEY = "api-key"

        val API_KEY_VALUE: String

        const val PAGE_SIZE = "page-size"

        const val DEFAULT_PAGE_SIZE = "DefaultPageSize"

        const val DEFAULT_PAGE_SIZE_VALUE = "25"

        init {
            System.loadLibrary("keys")
            API_KEY_VALUE = String(Base64.decode(getNativeApiKey(), Base64.DEFAULT))
        }

        @JvmStatic
        private external fun getNativeApiKey(): String
    }
}