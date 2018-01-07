package com.example.dimi.reactiveclean.presentation.NewsMain.presenter

import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.NewsMainInterractor
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigator
import javax.inject.Inject

class NewsMainPresenterImpl
@Inject constructor(val interractor: NewsMainInterractor,
                    val mapper: NewsMainDomainMapper,
                    private val navigator: NewsMainNavigator) : NewsMainPresenter {

    init {
        navigator.startNavigation()
    }

    override fun disposeSubscriptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onContentClicked() {
        navigator.navigateToContent()
    }

    override fun onSectionsClicked() {
        navigator.navigateToSections()
    }
}