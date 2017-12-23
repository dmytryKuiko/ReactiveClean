package com.example.dimi.reactiveclean.domain.NewsMain.sections

import com.example.dimi.reactiveclean.models.Section
import io.reactivex.Flowable

interface NewsMainSectionsInterractor {

    fun getSectionsStream(): Flowable<List<Section>>
    fun getSpecificSectionsStream(params: String): Flowable<List<Section>>
}