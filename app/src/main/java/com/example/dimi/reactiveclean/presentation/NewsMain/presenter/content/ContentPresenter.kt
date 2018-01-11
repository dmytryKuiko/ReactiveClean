package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.utils.paginator.PaginatorModelResult
import io.reactivex.Observable

interface ContentPresenter : BaseDataPresenter<PaginatorModelResult<ContentDisplayable>> {

    fun getSingleEventData(): LiveData<String>

    fun refreshContent()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun setVisibleItem(position: Int)

    fun getVisibleItem(): Int

    fun subscribeSearchText(text: Observable<String>)

    fun disposeRxBinding()
}