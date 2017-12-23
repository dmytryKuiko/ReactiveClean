package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.SectionDisplayable

interface NewsMainSectionsPresenter : BaseDataPresenter<List<SectionDisplayable>> {

    fun getError(): LiveData<Unit>
}