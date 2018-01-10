package com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen

import com.example.dimi.reactiveclean.data.NewsMain.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.NewsMain.section_chosen.SectionChosenStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SectionChosenRepositoryImpl
@Inject constructor(private val store: SectionChosenStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: ContentDataMapperForDB,
                    @Named(DiConstants.SECTION_CHOSEN_URL) private val sectionChosen: String) : SectionChosenRepository {

    override fun getSectionContent(page: Int): Single<List<Content>> {
        return serviceNewsApi.getSectionChosen(sectionChosen, page).map(mapper)
    }
}