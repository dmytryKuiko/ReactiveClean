package com.example.dimi.reactiveclean.presentation.Main.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseViewModel
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem

interface MainPresenter : BaseViewModel<List<ArticleDisplayableItem>> {

    fun getProgress(): LiveData<Int>

    fun getError(): LiveData<Unit>

    fun getSuccess(): LiveData<Unit>

    fun onRefreshClicked()
}