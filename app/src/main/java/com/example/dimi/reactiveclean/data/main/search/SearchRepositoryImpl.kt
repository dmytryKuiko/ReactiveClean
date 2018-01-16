package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.data.main.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl
@Inject constructor(
        private val store: SearchStore,
        private val newsApi: ServiceNewsApi,
        private val mapper: ContentDataMapperForDB
) : SearchRepository {
    override fun storeSearch(search: SearchModel) {
        store.store(search)
    }

    override fun getSearches(count: Int): Flowable<List<SearchModel>> {
        return store.getSearches(count)
    }

    override fun getResultsForSearch(search: SearchModel): Single<List<Content>> {
        return newsApi.getSearchContent(search.text).map(mapper)
    }
}