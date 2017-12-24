package com.example.dimi.reactiveclean.data.NewsMain.sections

import com.example.dimi.reactiveclean.data.db.SectionsDao
import com.example.dimi.reactiveclean.models.sections.Section
import io.reactivex.Flowable
import javax.inject.Inject

class NewsMainSectionsStoreImpl
@Inject constructor(private val sectionsDao: SectionsDao): NewsMainSectionsStore {

    override fun storeAll(list: List<Section>) = sectionsDao.insert(list.toTypedArray())

    override fun getAllSections(): Flowable<List<Section>> = sectionsDao.getAllSections()

    override fun getSpecificSections(params: String): Flowable<List<Section>> {
        return sectionsDao.getSpecificSections(params)
    }
}