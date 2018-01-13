package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.domain.main.NewsMainDomainMapper
import com.example.dimi.reactiveclean.domain.main.NewsMainInterractor
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import io.reactivex.Observable
import javax.inject.Inject

class NewsMainPresenterImpl
@Inject constructor(
        val interractor: NewsMainInterractor,
        val mapper: NewsMainDomainMapper,
        private val navigator: NewsMainNavigator,
        private val menuController: MenuController
) : NewsMainPresenter {

    init {
//        navigator.startNavigation()
    }

    override fun isMenuOpen(): Observable<Boolean> = menuController.isOpen()
}