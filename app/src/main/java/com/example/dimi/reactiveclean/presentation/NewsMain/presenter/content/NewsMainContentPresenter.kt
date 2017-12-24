package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent

interface NewsMainContentPresenter : BaseDataPresenter<List<ContentDisplayable>> {

    fun getError(): LiveData<Unit>
}