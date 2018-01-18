package com.example.dimi.reactiveclean.data.main.sectionChosen

import com.example.dimi.reactiveclean.data.main.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.section.ContentChosen
import io.reactivex.Single
import javax.inject.Inject

class SectionChosenRepositoryImpl
@Inject constructor(
        private val store: SectionChosenStore,
        private val serviceNewsApi: ServiceNewsApi,
        private val mapper: ContentDataMapperForDB,
        private val contentChosen: ContentChosen
) : SectionChosenRepository {

    override fun getSectionContent(page: Int): Single<List<Content>> {
        return serviceNewsApi.getSectionChosen(contentChosen.url, page, contentChosen.query).map(mapper)
    }
}