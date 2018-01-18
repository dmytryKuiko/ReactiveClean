package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.data.db.SectionsDao
import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable
import javax.inject.Inject

class SectionStoreImpl
@Inject constructor(private val sectionsDao: SectionsDao) : SectionStore {

    override fun storeAll(list: List<Section>) = sectionsDao.insert(list.toTypedArray())

    override fun getAllSections(): Flowable<List<Section>> = sectionsDao.getAllSections()

    override fun getSpecificSections(params: String): Flowable<List<Section>> {
        return sectionsDao.getSpecificSections(params)
    }
}