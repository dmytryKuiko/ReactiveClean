package com.example.dimi.reactiveclean.data.NewsMain.content

import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentResponse

interface NewsMainContentManager {

    fun saveContentNavigation(response: ContentResponse)

    fun errorResponse(throwable: Throwable)

    fun nextPageExists(): Boolean

    fun lastError(): Boolean

    fun getNextPage(): Int
}