package com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult

interface SectionChosenPresenter : BaseDataPresenter<PaginatorModelResult<ContentDisplayable>> {

    fun getNetworkError(): LiveData<String>

    fun refreshContent()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun setVisibleItem(position: Int)

    fun getVisibleItem(): Int

    fun getTitle(): String

    fun onBackPressed()

    fun openMenu()
}