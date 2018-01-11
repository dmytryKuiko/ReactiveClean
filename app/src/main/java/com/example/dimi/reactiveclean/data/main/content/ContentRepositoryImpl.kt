package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ContentRepositoryImpl
@Inject constructor(private val store: ContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: ContentDataMapper,
                    private val mapperDB: ContentDataMapperForDB) : ContentRepository {

    override fun getAllContent(): Flowable<List<Content>> =
            store.getAll()

    override fun deleteAndFetchContent(): Completable =
            serviceNewsApi.getAllContent()
                    .map(mapperDB)
                    .doOnSuccess(store::deleteAllAndStoreAll)
                    .toCompletable()

    override fun searchContent(text: String): Single<ContentPages> {
        return serviceNewsApi.getSpecificContent(text).map(mapper)
    }

    override fun loadNextContentPage(page: Int): Completable {
        return serviceNewsApi.getNextContent(page)
                .map(mapperDB)
                .doOnSuccess(store::storeAll)
                .toCompletable()
    }
}
