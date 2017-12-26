package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentManager
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.Loading
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentRepositoryImpl
@Inject constructor(private val store: NewsMainContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainContentDataMapper,
                    private val contentManager: NewsMainContentManager) : NewsMainContentRepository {

    override fun getSpecificContentStream(params: String): Single<List<Content>> =
            serviceNewsApi.getSpecificContent(params).map(mapper)

    override fun getAllContent(): Flowable<List<BaseItem>> =
            store.getAll()
                    .flatMap(this::addLoadingItem)

    override fun loadMoreContent(): Completable =
            when (contentManager.nextPageExists() && !contentManager.lastError()) {
                true -> loadNextContent()
                false -> deleteAndFetchContent()
            }

    override fun deleteAndFetchContent(): Completable =
            serviceNewsApi.getAllContent()
                    .compose(this::composeSingle)
                    .doOnSuccess(store::deleteAllAndStoreAll)
                    .toCompletable()

    private fun loadNextContent(): Completable =
            serviceNewsApi.getNextContent(contentManager.getNextPage())
                    .compose(this::composeSingle)
                    .doOnSuccess(store::storeAll)
                    .toCompletable()

    private fun addLoadingItem(list: List<BaseItem>): Flowable<List<BaseItem>> =
            when (contentManager.nextPageExists()) {
                true -> {
                    val mutableList: MutableList<BaseItem> = mutableListOf()
                    with(mutableList) {
                        addAll(list)
                        add(Loading(true))
                    }
                    Flowable.just(mutableList)
                }
                false -> Flowable.just(list)
            }

    private fun composeSingle(single: Single<ContentResponse>): Single<List<Content>> =
            single.doOnSuccess(contentManager::saveContentNavigation)
                    .doOnError(contentManager::errorResponse)
                    .map(mapper)
}