package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.presentation.BasePresenter
import io.reactivex.Observable

interface NewsMainPresenter : BasePresenter {

    fun onContentClicked()

    fun onSectionsClicked()

    fun isMenuOpen(): Observable<Boolean>
}