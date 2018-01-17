package com.example.dimi.reactiveclean.domain.main.content

import com.example.dimi.reactiveclean.extensions.toDisplayable
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.functions.Function
import org.joda.time.DateTime
import javax.inject.Inject

class ContentDomainMapper
@Inject constructor() : Function<List<Content>, List<ContentDisplayable.Content>> {

    override fun apply(list: List<Content>): List<ContentDisplayable.Content> {
        val parsedList: MutableList<ContentDisplayable.Content> = mutableListOf()
        list.forEach {
            val temp = DateTime(it.publicationMills).toDisplayable()
            parsedList.add(ContentDisplayable.Content(title = it.name, url = it.webUrl, time = temp, sectionName = it.sectionName))
        }
        return parsedList
    }
}