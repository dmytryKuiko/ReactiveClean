package com.example.dimi.reactiveclean.repositories.NewsMain.sections

import com.example.dimi.reactiveclean.models.Section
import io.reactivex.Completable
import io.reactivex.Flowable

interface NewsMainSectionsRepository {

    fun getAllSections(): Flowable<List<Section>>

    fun fetchSections(): Completable

    fun deleteAndFetchSections(): Completable
}