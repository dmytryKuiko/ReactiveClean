package com.example.dimi.reactiveclean.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Checks whether the end of the data reached
 * If yes, ServerError would be thrown
 */
class ErrorInterceptor
@Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response.code()

        if (code in 400..500) throw ServerError(code)

        return response
    }
}