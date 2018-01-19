package com.example.dimi.reactiveclean.presentation.main.presenter.section

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.section.SectionDisplayable

interface SectionPresenter : BaseDataPresenter<List<SectionDisplayable>> {

    fun getSingleEventLiveData(): LiveData<String>

    fun openCurrentSection(section: SectionDisplayable.Section)

    fun openMenuClicked()
}