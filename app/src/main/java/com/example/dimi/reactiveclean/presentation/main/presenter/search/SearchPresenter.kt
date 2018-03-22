package com.example.dimi.reactiveclean.presentation.main.presenter.search

import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import io.reactivex.Observable

interface SearchPresenter : BaseDataPresenter<List<SearchDisplayable.Search>> {

    fun disposeRxBinding()

    fun previousSearchClicked(search: SearchDisplayable.Search)

    fun listenEditText(listener: Observable<String>)

    fun listenEditTextModel(listener: Observable<EditTextBindingModel>)

    fun onBackPressed()
}