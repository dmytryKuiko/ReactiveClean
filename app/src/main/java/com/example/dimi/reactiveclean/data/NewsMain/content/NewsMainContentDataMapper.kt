package com.example.dimi.reactiveclean.data.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import io.reactivex.functions.Function
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class NewsMainContentDataMapper
@Inject constructor(): Function<ContentResponse, List<Content>> {
    override fun apply(response: ContentResponse): List<Content> {
        val responseList = response.results
        val parsedList: MutableList<Content> = mutableListOf()
        responseList?.forEach {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val time = formatter.parse(it.webPublicationDate)
            if (it.id != null && it.type != null && it.sectionId != null && it.sectionName != null
                    && time != null && it.webTitle != null && it.webUrl != null
                    && it.apiUrl != null && it.pillarName != null) {
                parsedList.add(Content(it.id, it.type, it.sectionName, Date(time.time),
                        it.webUrl, it.pillarName))
            }
        }
        return parsedList
    }
}