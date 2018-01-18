package com.example.dimi.reactiveclean.presentation.main.presenter

import io.reactivex.Observable

interface MenuController {

    fun open()

    fun close()

    fun isOpen(): Observable<Boolean>
}