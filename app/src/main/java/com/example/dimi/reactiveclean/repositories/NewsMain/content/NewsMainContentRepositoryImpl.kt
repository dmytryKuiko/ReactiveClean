package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapperForDB
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentRepositoryImpl
@Inject constructor(private val store: NewsMainContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainContentDataMapper,
                    private val mapperDB: NewsMainContentDataMapperForDB) : NewsMainContentRepository {

//    override fun getSpecificContentStream(params: String): Single<List<Content>> =
//            serviceNewsApi.getSpecificContent(params).map(mapper)

    override fun getAllContent(): Flowable<List<Content>> =
            store.getAll()

    override fun loadMoreContent(page: Int): Single<ContentPages> =
            serviceNewsApi.getNextContent(page)
                    .doOnSuccess(this::mapAndStore)
                    .map(mapper)

    override fun deleteAndFetchContent(): Single<ContentPages> =
            serviceNewsApi.getAllContent()
                    .doOnSuccess(this::mapAndDeleteAndStore)
                    .map(mapper)

    private fun mapAndStore(response: ContentResponse) {
        val list = mapperDB.apply(response)
        store.storeAll(list)
    }

    private fun mapAndDeleteAndStore(response: ContentResponse) {
        val list = mapperDB.apply(response)
        store.deleteAllAndStoreAll(list)
    }
}
