package com.example.dimi.reactiveclean.presentation.NewsMain.presenter

import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractor
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import javax.inject.Inject

class NewsMainPresenterImpl
@Inject constructor(val interractor: NewsMainInterractor,
                    val mapper: NewsMainDomainMapper) : NewsMainPresenter {
    override fun disposeSubscriptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onContentClicked() {
        interractor.onContentClicked()
    }

    override fun onSectionsClicked() {
        interractor.onSectionsClicked()
    }

    override fun listenField(textListener: InitialValueObservable<TextViewTextChangeEvent>) {
        interractor.makeSearchText(textListener)
    }
}