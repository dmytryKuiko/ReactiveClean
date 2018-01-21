package com.example.dimi.reactiveclean.presentation.main.presenter

import io.reactivex.Observable

interface MainPresenter {

    fun isMenuOpen(): Observable<Boolean>
}