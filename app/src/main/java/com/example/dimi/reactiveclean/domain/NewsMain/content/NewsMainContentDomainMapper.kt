package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.Loading
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainContentDomainMapper
@Inject constructor() : Function<List<Content>, List<ContentDisplayable>> {
    override fun apply(list: List<Content>): List<ContentDisplayable> {
        val parsedList: MutableList<ContentDisplayable> = mutableListOf()
        list.forEach {
            parsedList.add(ContentDisplayable(it.name, it.webUrl))
        }
        return parsedList
    }
}