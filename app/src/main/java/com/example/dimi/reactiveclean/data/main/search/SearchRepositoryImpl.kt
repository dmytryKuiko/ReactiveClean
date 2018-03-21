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

    /**
     * Stores Search in Database
     * @param search data to be Stored
     */
    override fun storeSearch(search: SearchModel) {
        store.store(search)
    }

    /**
     * Retrieves Searches from DB
     * @param count a number of Searches to be retrieved from DB
     * @return data from DB
     */
    override fun getSearches(count: Int): Flowable<List<SearchModel>> {
        return store.getSearches(count)
    }

    /**
     * Requests data for chosen Search
     * @param text info for a request
     * @return all appropriate found data
     */
    override fun getResultsForSearch(text: String): Single<List<Content>> {
        return newsApi.getSearchContent(text).map(mapper)
    }
}