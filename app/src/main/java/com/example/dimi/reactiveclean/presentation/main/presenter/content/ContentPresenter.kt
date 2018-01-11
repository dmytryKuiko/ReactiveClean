package com.example.dimi.reactiveclean.presentation.main.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
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