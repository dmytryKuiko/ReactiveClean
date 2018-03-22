package com.example.dimi.reactiveclean.domain.main.search

import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Observable

interface SearchInteractor {

    fun listenSymbolTyped(listener: Observable<String>): Observable<List<SearchModel>>

    fun listenActionDone(listener: Observable<EditTextBindingModel>): Observable<String>
}