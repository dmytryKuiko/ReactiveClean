package com.example.dimi.reactiveclean.domain.main.section

import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable

interface SectionInteractor {

    fun getSectionsStream(): Flowable<List<Section>>

    fun getSpecificSectionsStream(params: String): Flowable<List<Section>>
}