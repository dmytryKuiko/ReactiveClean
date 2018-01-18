package com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.models.section.ToolbarData

interface SectionChosenPresenter : BaseDataPresenter<PaginatorModelResult<ContentDisplayable>> {

    fun getNetworkError(): LiveData<String>

    fun refreshContent()

    fun loadNextContentPage()

    fun openCurrentContent(content: ContentDisplayable.Content)

    fun onBackPressed()

    fun navigationClicked()

    fun searchClicked()

    fun getToolbarData(): LiveData<ToolbarData>
}