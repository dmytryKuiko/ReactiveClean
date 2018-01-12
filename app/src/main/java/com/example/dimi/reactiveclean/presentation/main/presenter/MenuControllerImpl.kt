package com.example.dimi.reactiveclean.presentation.main.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import javax.inject.Inject

class MenuControllerImpl
@Inject constructor() : MenuController {

    private val state: BehaviorRelay<Boolean> = BehaviorRelay.createDefault(false)

    override fun open() {
        state.accept(true)
    }

    override fun close() {
        state.accept(false)
    }

    override fun isOpen(): Observable<Boolean> = state
}