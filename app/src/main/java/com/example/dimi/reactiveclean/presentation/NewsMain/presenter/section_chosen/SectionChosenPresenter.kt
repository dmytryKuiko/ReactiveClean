package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.utils.paginator.PaginatorModelResult

interface SectionChosenPresenter : BaseDataPresenter<PaginatorModelResult<ContentDisplayable>> {

    fun getNetworkError(): LiveData<String>

    fun refreshContent()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun setVisibleItem(position: Int)

    fun getVisibleItem(): Int

    fun getSectionChosen(): String
}