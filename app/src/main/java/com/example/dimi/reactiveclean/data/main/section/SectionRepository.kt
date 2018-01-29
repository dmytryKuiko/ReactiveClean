package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Completable
import io.reactivex.Flowable

interface SectionRepository {

    fun getAllSections(): Flowable<List<Section>>

    fun fetchSections(): Completable

    fun getSpecificSections(params: String): Flowable<List<Section>>
}