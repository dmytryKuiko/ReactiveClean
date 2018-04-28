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

    /**
     * Retrieves all Sections from DB
     * @return data from DB
     */
    override fun getAllSections(): Flowable<List<Section>> = store.getAllSections()

    /**
     * Loads data from a Network if successful stores in DB
     * @return completable whther it was successful or not
     */
    override fun fetchSections(): Completable = loadSections()
        .doOnSuccess(store::storeAll)
        .ignoreElement()

    /**
     * Retrieves specific Section from DB
     * @param params parameters for looking into DB
     * @return satisfied result for a given parameter
     */
    override fun getSpecificSections(params: String): Flowable<List<Section>> {
        return store.getSpecificSections(params)
    }

    /**
     * Loads data from Network and maps it into desirable model
     */
    private fun loadSections(): Single<List<Section>> = serviceNewsApi.getAllSections()
        .map(mapper)
}