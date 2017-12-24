package com.example.dimi.reactiveclean.presentation.NewsMain.presenter

import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractor
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigatorImpl
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content.NewsMainContentPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections.NewsMainSectionsPresenter
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.RxTextView
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

    override fun listenField(textChangeEvents: InitialValueObservable<TextViewTextChangeEvent>) {
        interractor.makeSearchText(textChangeEvents)
    }
}