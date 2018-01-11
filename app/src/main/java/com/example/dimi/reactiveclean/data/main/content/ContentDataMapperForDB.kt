package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import io.reactivex.functions.Function
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import javax.inject.Inject

class ContentDataMapperForDB
@Inject constructor() : Function<ContentResponse, List<Content>> {
    override fun apply(response: ContentResponse): List<Content> {
        val responseList = response.results
        val parsedList: MutableList<Content> = mutableListOf()
        responseList?.forEach {

            if (it.id != null && it.type != null && it.sectionId != null && it.sectionName != null
                    && it.webPublicationDate != null && it.webTitle != null && it.webUrl != null
                    && it.apiUrl != null && it.pillarName != null) {
                val publicationMills = DateTime(it.webPublicationDate, DateTimeZone.UTC).millis
                parsedList.add(
                        Content(name = it.webTitle, type = it.type, sectionName = it.sectionName,
                                publicationMills = publicationMills, webUrl = it.webUrl, pillarName = it.pillarName
                        )
                )
            }
        }
        return parsedList
    }
}