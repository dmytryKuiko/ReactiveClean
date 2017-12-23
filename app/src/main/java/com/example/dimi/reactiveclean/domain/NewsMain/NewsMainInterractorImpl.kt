package com.example.dimi.reactiveclean.domain.NewsMain

import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigator
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigatorStep
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsMainInterractorImpl
@Inject constructor(private val navigator: NewsMainNavigator,
                    private val contentInterractor: NewsMainContentInterractor,
                    private val sectionsInterractor: NewsMainSectionsInterractor) : NewsMainInterractor {

    init {
        navigator.startNavigation()
    }

    override fun onContentClicked() {
        navigator.navigateToContent()
    }

    override fun onSectionsClicked() {
        navigator.navigateToSections()
    }

    override fun makeSearchText(textLisnter: InitialValueObservable<TextViewTextChangeEvent>) {
        textLisnter.debounce(300, TimeUnit.MILLISECONDS)
                .map { textViewTextChangeEvent -> textViewTextChangeEvent.text().toString() }
                .filter { string -> string.length > 2 }
                .distinctUntilChanged()
//                .switchMap { string -> when(navigator.getCurrentStep()) {
//                    NewsMainNavigatorStep.CONTENT -> TODO()
//                    else -> TODO()
//                } }
//        when (navigator.getCurrentStep()) {
//            NewsMainNavigatorStep.CONTENT -> TODO()
//            NewsMainNavigatorStep.SECTIONS -> TODO()
//            else -> throw IllegalStateException("Current State cannot be none")
//        }
    }
}