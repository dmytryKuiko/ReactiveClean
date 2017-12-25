package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.models.LoadingDisplayable
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.Loading
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainContentDomainMapper
@Inject constructor() : Function<List<BaseItem>, List<BaseItemDisplayable>> {
    override fun apply(list: List<BaseItem>): List<BaseItemDisplayable> {
        val parsedList: MutableList<BaseItemDisplayable> = mutableListOf()
        list.forEach {
            when(it) {
                is Content -> parsedList.add(ContentDisplayable(it.name, it.webUrl))
                is Loading -> parsedList.add(LoadingDisplayable(it.showLoading))
            }
        }
        return parsedList
    }
}