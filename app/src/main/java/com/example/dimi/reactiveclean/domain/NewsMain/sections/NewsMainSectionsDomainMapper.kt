package com.example.dimi.reactiveclean.domain.NewsMain.sections

import com.example.dimi.reactiveclean.models.Section
import com.example.dimi.reactiveclean.models.SectionDisplayable
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainSectionsDomainMapper
@Inject constructor() : Function<List<Section>, List<SectionDisplayable>> {
    override fun apply(list: List<Section>): List<SectionDisplayable> {
        val convertedList: MutableList<SectionDisplayable> = mutableListOf()
        list.forEach {
            convertedList.add(SectionDisplayable(it.webTitle, it.apiUrl))
        }
        return convertedList
    }
}