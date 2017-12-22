package com.example.dimi.reactiveclean.domain.NewsMain

import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent

interface NewsMainInterractor {

    fun onContentClicked()

    fun onSectionsClicked()

    fun makeSearchText(textLisnter: InitialValueObservable<TextViewTextChangeEvent>)
}