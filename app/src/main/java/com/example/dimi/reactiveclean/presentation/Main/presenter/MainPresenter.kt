package com.example.dimi.reactiveclean.presentation.Main.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem

interface MainPresenter : BaseDataPresenter<List<ArticleDisplayableItem>> {

    fun getProgress(): LiveData<Int>

    fun getError(): LiveData<Unit>

    fun getSuccess(): LiveData<Unit>

    fun onRefreshClicked()
}