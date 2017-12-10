package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import com.example.dimi.reactiveclean.base.BasePresenter
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.FirstScreenView

interface FirstScreenPresenter : BasePresenter<FirstScreenView> {

    fun onRefreshClicked()

    fun clearSubscriptions()
}