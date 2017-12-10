package com.example.dimi.reactiveclean.presentation.FirstScreen.view

import com.example.dimi.reactiveclean.base.BaseView
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem

interface FirstScreenView : BaseView<List<ArticleDisplayableItem>> {

    fun showDataSynchronized()

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun disableRefreshButton()

    fun enableRefreshButton()
}