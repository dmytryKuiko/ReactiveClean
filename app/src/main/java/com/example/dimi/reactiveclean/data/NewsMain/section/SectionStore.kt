package com.example.dimi.reactiveclean.data.NewsMain.section

import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable

interface SectionStore {

    fun storeAll(list: List<Section>)

    fun getAllSections(): Flowable<List<Section>>

    fun getSpecificSections(params: String): Flowable<List<Section>>
}