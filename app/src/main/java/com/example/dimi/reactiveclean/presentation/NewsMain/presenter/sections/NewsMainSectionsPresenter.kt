package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.sections.SectionDisplayable
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent

interface NewsMainSectionsPresenter : BaseDataPresenter<List<SectionDisplayable>> {

    fun getError(): LiveData<Unit>
}