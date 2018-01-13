package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.presentation.BasePresenter
import io.reactivex.Observable

interface NewsMainPresenter {

    fun isMenuOpen(): Observable<Boolean>
}