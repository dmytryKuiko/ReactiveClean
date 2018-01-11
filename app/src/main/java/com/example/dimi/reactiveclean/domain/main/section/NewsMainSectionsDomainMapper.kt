package com.example.dimi.reactiveclean.domain.main.section

import com.example.dimi.reactiveclean.models.section.Section
import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainSectionsDomainMapper
@Inject constructor() : Function<List<Section>, List<SectionDisplayable>> {

    override fun apply(list: List<Section>): List<SectionDisplayable> {
        val convertedList: MutableList<SectionDisplayable> = mutableListOf()
        list.forEach {
            convertedList.add(SectionDisplayable.Section(it.webTitle, it.name))
        }
        return convertedList
    }
}