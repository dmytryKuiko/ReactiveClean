package com.example.dimi.reactiveclean.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            request = request.newBuilder().addHeader("Authorization", "fooToken").build()
        }
        return chain.proceed(request)
    }
}