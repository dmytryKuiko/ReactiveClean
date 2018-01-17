package com.example.dimi.reactiveclean.presentation.main.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import io.reactivex.Observable

interface ContentPresenter : BaseDataPresenter<PaginatorModelResult<ContentDisplayable>> {

    fun getSingleEventData(): LiveData<String>

    fun refreshClicked()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun subscribeSearchText(text: Observable<String>)

    fun disposeRxBinding()

    fun openDrawerClicked()

    fun searchClicked()
}