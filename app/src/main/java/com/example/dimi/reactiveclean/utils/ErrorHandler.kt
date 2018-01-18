package com.example.dimi.reactiveclean.utils

import com.example.dimi.reactiveclean.data.network.ServerError
import com.example.dimi.reactiveclean.models.content.ContentState
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    fun getErrorState(error: Throwable): ContentState {
        if (error is ServerError) {
            return when (error.code) {
                400 -> ContentState.ALL_DATA
                else -> ContentState.ERROR
            }
        }
        return ContentState.ERROR
    }
}