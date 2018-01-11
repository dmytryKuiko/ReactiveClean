package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.models.section.Section
import com.example.dimi.reactiveclean.models.section.SectionResponse
import io.reactivex.functions.Function
import javax.inject.Inject

class SectionDataMapper
@Inject constructor() : Function<SectionResponse, List<Section>> {
    override fun apply(response: SectionResponse): List<Section> {
        val listResults = response.results
        val listParsed: MutableList<Section> = mutableListOf()
        listResults?.forEach {
            if (it.id != null && it.apiUrl != null && it.webTitle != null && it.webUrl != null) {
                listParsed.add(Section(name = it.id, webTitle = it.webTitle, webUrl = it.webUrl, apiUrl = it.apiUrl))
            } else {
                throw NoSuchFieldException("While parsing Section")
            }
        }
        return listParsed
    }
}