package com.example.dimi.reactiveclean.data.NewsMain.content

import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentResponse
import javax.inject.Inject

class NewsMainContentManagerImpl
@Inject constructor(private var contentPages: ContentPages) : NewsMainContentManager {
    override fun saveContentNavigation(response: ContentResponse) {
        with(response) {
            if (currentPage != null && pageSize != null && pages != null && startIndex != null) {
                contentPages = ContentPages(currentPage, pageSize, pages, startIndex, false)
            } else {
                throw NoSuchFieldException("Filed is missing for navigation in ContentResponse ")
            }
        }
    }

    override fun errorResponse(throwable: Throwable) {
        contentPages = ContentPages(0, 0, 0, 0, true)
    }

    override fun nextPageExists(): Boolean =
            contentPages.currentPage < contentPages.pages

    override fun lastError(): Boolean =
            contentPages.error

    override fun getNextPage(): Int =
            contentPages.currentPage + 1
}
