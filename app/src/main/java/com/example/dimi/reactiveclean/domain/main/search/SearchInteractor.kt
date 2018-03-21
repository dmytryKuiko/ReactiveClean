package com.example.dimi.reactiveclean.domain.main.search

import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Observable

interface SearchInteractor {

    fun storeSearch(text: String)

    fun searchTyped(listener: Observable<String>): Observable<List<SearchModel>>

    fun actionKeyboardTyped(listener: Observable<EditTextBindingModel>): Observable<String>
}