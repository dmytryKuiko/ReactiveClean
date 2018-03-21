package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.data.db.SectionsDao
import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable
import javax.inject.Inject

class SectionStoreImpl
@Inject constructor(private val sectionsDao: SectionsDao) : SectionStore {

    /**
     * Stores Sections into DB
     * @param list data to be stored
     */
    override fun storeAll(list: List<Section>) = sectionsDao.insert(list.toTypedArray())

    /**
     * Retrieves all Section data from DB
     * @return data from DB
     */
    override fun getAllSections(): Flowable<List<Section>> = sectionsDao.getAllSections()

    /**
     * Retrieves specific Section from DB
     * @param params value for searching in DB
     * @return data from DB
     */
    override fun getSpecificSections(params: String): Flowable<List<Section>> {
        return sectionsDao.getSpecificSections(params)
    }
}