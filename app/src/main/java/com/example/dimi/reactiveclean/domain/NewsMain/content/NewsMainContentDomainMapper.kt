package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Item
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainContentDomainMapper
@Inject constructor() : Function<List<Content>, List<Item.Content>> {
    override fun apply(list: List<Content>): List<Item.Content> {
        val parsedList: MutableList<Item.Content> = mutableListOf()
        list.forEach {
            parsedList.add(Item.Content(it.name, it.webUrl))
        }
        return parsedList
    }
}