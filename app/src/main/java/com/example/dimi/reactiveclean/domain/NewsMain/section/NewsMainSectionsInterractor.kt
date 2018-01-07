package com.example.dimi.reactiveclean.domain.NewsMain.section

import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable

interface NewsMainSectionsInterractor {

    fun getSectionsStream(): Flowable<List<Section>>

    fun getSpecificSectionsStream(params: String): Flowable<List<Section>>
}