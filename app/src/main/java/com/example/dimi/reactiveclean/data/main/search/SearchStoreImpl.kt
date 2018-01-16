package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.data.db.SearchDao
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import javax.inject.Inject

class SearchStoreImpl
@Inject constructor(private val searchDao: SearchDao) : SearchStore {

    override fun store(search: SearchModel) {
        searchDao.insert(search)
    }

    override fun getSearches(count: Int): Flowable<List<SearchModel>> = searchDao.getSearches(count)
}