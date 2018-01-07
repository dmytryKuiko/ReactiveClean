package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.section.SectionDisplayable

interface SectionPresenter : BaseDataPresenter<List<SectionDisplayable>> {

    fun getSingleEventLiveData(): LiveData<String>

    fun openCurrentSection(section: SectionDisplayable.Section)
}