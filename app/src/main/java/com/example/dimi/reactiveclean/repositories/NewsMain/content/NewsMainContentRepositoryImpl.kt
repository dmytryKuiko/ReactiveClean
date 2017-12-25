package com.example.dimi.reactiveclean.repositories.NewsMain.content

import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentDataMapper
import com.example.dimi.reactiveclean.data.NewsMain.content.NewsMainContentStore
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.LoadingDisplayable
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentNavigation
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.Loading
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import javax.inject.Inject

class NewsMainContentRepositoryImpl
@Inject constructor(private val store: NewsMainContentStore,
                    private val serviceNewsApi: ServiceNewsApi,
                    private val mapper: NewsMainContentDataMapper) : NewsMainContentRepository {

    private lateinit var contentNavigation: ContentNavigation

    override fun getSpecificContentStream(params: String): Single<List<Content>> =
            serviceNewsApi.getSpecificContent(params).map(mapper)

    override fun getAllContent(): Flowable<List<BaseItem>> =
            store.getAll()
                    .flatMap(this::addLoadingItem)

    override fun loadMoreContent(): Completable =
            serviceNewsApi.getNextContent(contentNavigation.currentPage + 1)
                    .compose(this::composeSingle)
                    .doOnSuccess(store::storeAll)
                    .toCompletable()

    override fun deleteAndFetchContent(): Completable =
            serviceNewsApi.getAllContent()
                    .compose(this::composeSingle)
                    .doOnSuccess(store::deleteAllAndStoreAll)
                    .toCompletable()

    private fun addLoadingItem(list: List<BaseItem>): Flowable<List<BaseItem>> =
            when (contentNavigation.currentPage < contentNavigation.pages) {
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
            single.doOnSuccess(this::getContentNavigation)
                    .doOnError { contentNavigation = ContentNavigation(0,0,0,0) }
                    .map(mapper)

    private fun getContentNavigation(response: ContentResponse) {
        with(response) {
            if (currentPage != null && pageSize != null && pages != null && startIndex != null) {
                contentNavigation = ContentNavigation(currentPage, pageSize, pages, startIndex)
            } else {
                throw NoSuchFieldException("Filed is missing for navigation in ContentResponse ")
            }
        }
    }
}