package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.domain.main.NewsMainDomainMapper
import com.example.dimi.reactiveclean.domain.main.NewsMainInteractor
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import io.reactivex.Observable
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(
    val interactor: NewsMainInteractor,
    val mapper: NewsMainDomainMapper,
    private val navigator: NewsMainNavigator,
    private val menuController: MenuController
) : MainPresenter {

    override fun isMenuOpen(): Observable<Boolean> = menuController.isOpen()
}