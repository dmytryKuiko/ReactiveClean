package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.Single

interface SearchRepository {

    fun storeSearch(search: SearchModel)

    fun getSearches(count: Int): Flowable<List<SearchModel>>

    fun getResultsForSearch(text: String): Single<List<Content>>
}
