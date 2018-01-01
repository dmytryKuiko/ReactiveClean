package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import io.reactivex.Observable

interface NewsMainContentPresenter : BaseDataPresenter<ContentDisplayable> {

    fun refreshContent()

    fun loadNextContentPage()

    fun setVisibleItem(position: Int)

    fun getVisibleItem(): Int

    fun subscribeSearchText(text: Observable<String>)

    fun disposeRxBinding()
}