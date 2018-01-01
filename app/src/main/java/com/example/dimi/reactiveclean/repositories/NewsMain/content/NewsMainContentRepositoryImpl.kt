package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapperForDB
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentRepositoryImpl
@Inject constructor(private val store: NewsMainContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainContentDataMapper,
                    private val mapperDB: NewsMainContentDataMapperForDB) : NewsMainContentRepository {

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
