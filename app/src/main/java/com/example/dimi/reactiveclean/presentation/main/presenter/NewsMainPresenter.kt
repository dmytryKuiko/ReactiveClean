package com.example.dimi.reactiveclean.presentation.main.presenter

import io.reactivex.Observable

interface NewsMainPresenter {

    fun isMenuOpen(): Observable<Boolean>
}