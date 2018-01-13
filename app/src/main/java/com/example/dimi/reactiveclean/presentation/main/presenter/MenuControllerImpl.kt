package com.example.dimi.reactiveclean.presentation.main.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

class MenuControllerImpl
@Inject constructor() : MenuController {

    private val state: PublishRelay<Boolean> = PublishRelay.create()

    override fun open() {
        state.accept(true)
    }

    override fun close() {
        state.accept(false)
    }

    override fun isOpen(): Observable<Boolean> = state
}