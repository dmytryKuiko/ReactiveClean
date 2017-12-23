package com.example.dimi.reactiveclean.data.NewsMain.sections

import com.example.dimi.reactiveclean.models.Section
import io.reactivex.Flowable

interface NewsMainSectionsStore {

    fun storeAll(list: List<Section>)

    fun getAllSections(): Flowable<List<Section>>
}