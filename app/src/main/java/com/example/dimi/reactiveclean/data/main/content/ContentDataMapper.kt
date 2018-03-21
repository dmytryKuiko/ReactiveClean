package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.functions.Function
import java.io.NotSerializableException
import javax.inject.Inject

/**
 * Maps response from the server to desired model with paging info
 * Not used
 */
class ContentDataMapper
@Inject constructor() : Function<ContentResponse, ContentPages> {

    override fun apply(response: ContentResponse): ContentPages =
        with(response) {
            if (status != null && total != null && startIndex != null && pageSize != null
                && currentPage != null && pages != null) {
                ContentPages(status, total, startIndex, pageSize, currentPage, pages)
            } else {
                throw NotSerializableException("ContentResponse field is missing")
            }
        }
}