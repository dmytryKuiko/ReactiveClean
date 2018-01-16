package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable

interface SearchStore {

    fun store(search: SearchModel)

    fun getSearches(count: Int): Flowable<List<SearchModel>>
}