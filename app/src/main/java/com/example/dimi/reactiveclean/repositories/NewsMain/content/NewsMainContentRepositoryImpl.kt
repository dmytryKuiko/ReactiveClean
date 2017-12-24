package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentRepositoryImpl
@Inject constructor(private val store: NewsMainContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainContentDataMapper) : NewsMainContentRepository {

    override fun getSpecificContentStream(params: String): Single<List<Content>> {
        return serviceNewsApi.getSpecificContent(params).map(mapper)
    }

    override fun getAllContent(): Flowable<List<Content>> = store.getAll()

    override fun fetchContent(): Completable = loadContent()
            .doOnSuccess(store::storeAll)
            .toCompletable()

    override fun deleteAndFetchContent(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadContent(): Single<List<Content>> = serviceNewsApi.getAllContent().map(mapper)
}