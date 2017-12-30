package com.example.dimi.reactiveclean.presentation.NewsMain.presenter

import com.example.dimi.reactiveclean.base.BasePresenter
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent

interface NewsMainPresenter : BasePresenter {

    fun onContentClicked()

    fun onSectionsClicked()
}