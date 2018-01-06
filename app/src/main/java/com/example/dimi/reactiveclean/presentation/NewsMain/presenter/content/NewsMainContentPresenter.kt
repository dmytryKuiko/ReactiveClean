package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.utils.paginator.PaginatorResult
import io.reactivex.Observable

interface NewsMainContentPresenter : BaseDataPresenter<PaginatorResult<ContentDisplayable>> {

    fun refreshContent()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun setVisibleItem(position: Int)

    fun getVisibleItem(): Int

    fun subscribeSearchText(text: Observable<String>)

    fun disposeRxBinding()
}