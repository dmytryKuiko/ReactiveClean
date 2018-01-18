package com.example.dimi.reactiveclean.presentation.main.presenter

import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import javax.inject.Inject

class MainFragmentPresenterImpl
@Inject constructor(
    private val navigator: NewsMainNavigator
) : MainFragmentPresenter {

    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}