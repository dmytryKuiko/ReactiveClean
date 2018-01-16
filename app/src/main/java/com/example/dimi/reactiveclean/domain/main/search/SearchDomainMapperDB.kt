package com.example.dimi.reactiveclean.domain.main.search

import com.example.dimi.reactiveclean.extensions.toDisplayable
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.functions.Function
import org.joda.time.DateTime
import javax.inject.Inject

class SearchDomainMapperDB
@Inject constructor() : Function<List<SearchModel>, List<SearchDisplayable.Search>> {

    override fun apply(list: List<SearchModel>): List<SearchDisplayable.Search> {
        val parsedList: MutableList<SearchDisplayable.Search> = mutableListOf()
        list.forEach {
            val dateTime = DateTime(it.dateTime.time).toDisplayable()
            parsedList.add(SearchDisplayable.Search(text = it.text, dateTime = dateTime))
        }
        return parsedList
    }
}