package com.example.dimi.reactiveclean.data.NewsMain.sections

import com.example.dimi.reactiveclean.models.sections.Section
import com.example.dimi.reactiveclean.models.sections.SectionResponse
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainSectionsDataMapper
@Inject constructor() : Function<SectionResponse, List<Section>> {
    override fun apply(response: SectionResponse): List<Section> {
        val listResults = response.results
        val listParsed: MutableList<Section> = mutableListOf()
        listResults?.forEach {
            if (it.id != null && it.apiUrl != null && it.webTitle != null && it.webUrl != null) {
                listParsed.add(Section(it.id, it.webTitle, it.webUrl, it.apiUrl))
            } else {
                throw NoSuchFieldException("While parsing Section")
            }
        }
        return listParsed
    }
}