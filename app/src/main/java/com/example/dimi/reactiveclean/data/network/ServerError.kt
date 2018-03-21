package com.example.dimi.reactiveclean.data.network

/**
 * Used for indication whether the end of data reached or not
 * @see ErrorInterceptor
 */
class ServerError(val code: Int) : RuntimeException()