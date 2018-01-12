package com.example.dimi.reactiveclean.presentation.main.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

interface MenuController {

    fun open()

    fun close()

    fun isOpen(): Observable<Boolean>
}