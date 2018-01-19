package com.example.dimi.reactiveclean.presentation.main.presenter.about

import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import javax.inject.Inject

class AboutPresenterImpl
@Inject constructor(
    private val menuController: MenuController,
    private val navigator: NewsMainNavigator
) : AboutPresenter {

    override fun openMenuClicked() {
        menuController.open()
    }

    override fun onBackClicked() {
        navigator.onBackPressed()
    }
}