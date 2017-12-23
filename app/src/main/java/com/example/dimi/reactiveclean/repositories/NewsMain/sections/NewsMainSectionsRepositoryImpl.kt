package com.example.dimi.reactiveclean.repositories.NewsMain.sections

import com.example.dimi.reactiveclean.data.NewsMain.sections.NewsMainSectionsDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.sections.NewsMainSectionsStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.Section
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainSectionsRepositoryImpl
@Inject constructor(private val store: NewsMainSectionsStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainSectionsDataMapper) : NewsMainSectionsRepository {

    override fun getAllSections(): Flowable<List<Section>> = store.getAllSections()

    override fun fetchSections(): Completable = loadSections().doOnSuccess(store::storeAll)
            .toCompletable()

    override fun deleteAndFetchSections(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadSections(): Single<List<Section>> = serviceNewsApi.getAllSections()
            .map(mapper)
}