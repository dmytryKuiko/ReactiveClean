package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SectionRepositoryImpl
@Inject constructor(
    private val store: SectionStore,
    private val serviceNewsApi: ServiceNewsApi,
    private val mapper: SectionDataMapper
) : SectionRepository {

    override fun getAllSections(): Flowable<List<Section>> = store.getAllSections()

    override fun fetchSections(): Completable = loadSections()
        .doOnSuccess(store::storeAll)
        .toCompletable()

    override fun getSpecificSections(params: String): Flowable<List<Section>> {
        return store.getSpecificSections(params)
    }

    private fun loadSections(): Single<List<Section>> = serviceNewsApi.getAllSections()
        .map(mapper)
}