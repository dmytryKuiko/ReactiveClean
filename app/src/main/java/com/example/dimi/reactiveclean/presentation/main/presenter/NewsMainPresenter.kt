package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.presentation.BasePresenter
import io.reactivex.Observable

interface NewsMainPresenter : BasePresenter {

    fun isMenuOpen(): Observable<Boolean>
}