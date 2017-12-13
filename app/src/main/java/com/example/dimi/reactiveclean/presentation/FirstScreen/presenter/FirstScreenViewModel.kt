package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseViewModel
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem

interface FirstScreenViewModel: BaseViewModel<List<ArticleDisplayableItem>> {

    fun getProgress(): LiveData<Int>

    fun getError(): LiveData<Unit>

    fun getSuccess(): LiveData<Unit>

    fun onRefreshClicked()
}