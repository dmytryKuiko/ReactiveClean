package com.example.dimi.reactiveclean.data.NewsMain.sections

import com.example.dimi.reactiveclean.data.db.NewsDao
import com.example.dimi.reactiveclean.data.db.SectionsDao
import com.example.dimi.reactiveclean.models.Section
import io.reactivex.Flowable
import javax.inject.Inject

class NewsMainSectionsStoreImpl
@Inject constructor(private val sectionsDao: SectionsDao): NewsMainSectionsStore {

    override fun storeAll(list: List<Section>) = sectionsDao.insert(list.toTypedArray())

    override fun getAllSections(): Flowable<List<Section>> = sectionsDao.getAllSections()
}