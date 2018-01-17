package com.example.dimi.reactiveclean.data.main.sectionChosen

import com.example.dimi.reactiveclean.data.main.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.section.SectionChosenModel
import io.reactivex.Single
import javax.inject.Inject

class SectionChosenRepositoryImpl
@Inject constructor(
        private val store: SectionChosenStore,
        private val serviceNewsApi: ServiceNewsApi,
        private val mapper: ContentDataMapperForDB,
        private val sectionChosen: SectionChosenModel
) : SectionChosenRepository {

    override fun getSectionContent(page: Int): Single<List<Content>> {
        return serviceNewsApi.getSectionChosen(sectionChosen.url, page, sectionChosen.query).map(mapper)
    }
}