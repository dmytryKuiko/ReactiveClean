package com.example.dimi.reactiveclean.presentation.main.presenter

import io.reactivex.Observable
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(
    private val menuController: MenuController
) : MainPresenter {

    override fun isMenuOpen(): Observable<Boolean> = menuController.isOpen()
}