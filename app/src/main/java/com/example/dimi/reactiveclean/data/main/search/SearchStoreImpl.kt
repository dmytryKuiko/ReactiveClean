package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.data.db.SearchDao
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import javax.inject.Inject

class SearchStoreImpl
@Inject constructor(private val searchDao: SearchDao) : SearchStore {

    /**
     * Stores Search to DB
     */
    override fun store(search: SearchModel) {
        searchDao.insert(search)
    }

    /**
     * Retrieves all Searches from DB
     * @return found data
     */
    override fun getSearches(count: Int): Flowable<List<SearchModel>> = searchDao.getSearches(count)
}