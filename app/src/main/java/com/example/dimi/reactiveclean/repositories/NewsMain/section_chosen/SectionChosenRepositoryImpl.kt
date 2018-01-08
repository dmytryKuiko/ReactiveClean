package com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen

import com.example.dimi.reactiveclean.data.NewsMain.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.NewsMain.section_chosen.SectionChosenStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import javax.inject.Inject

class SectionChosenRepositoryImpl
@Inject constructor(private val store: SectionChosenStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: ContentDataMapperForDB) : SectionChosenRepository {
}