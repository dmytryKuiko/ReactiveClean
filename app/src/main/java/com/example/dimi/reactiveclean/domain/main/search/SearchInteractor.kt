package com.example.dimi.reactiveclean.domain.main.search

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface SearchInteractor {

    fun storeSearch(text: String)

    fun getSearches(): Flowable<List<SearchModel>>

    fun getResultsForSearch(text: String): Single<List<Content>>

    fun searchTyped(listener: Observable<String>): Observable<List<SearchModel>>

    fun actionKeyboardTyped(listener: Observable<EditTextBindingModel>): Observable<EditTextBindingModel>
}