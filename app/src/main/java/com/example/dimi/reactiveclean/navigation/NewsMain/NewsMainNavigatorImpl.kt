package com.example.dimi.reactiveclean.navigation.NewsMain

import com.example.dimi.reactiveclean.navigation.extended.ExtendedRouter
import com.example.dimi.reactiveclean.navigation.RouterConstants
import javax.inject.Inject

class NewsMainNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : NewsMainNavigator {

    private val startPath = "search"

    private var currentStep = NewsMainNavigatorStep.NONE

    override fun startNavigation() {
        if (currentStep != NewsMainNavigatorStep.NONE) {
            return
        }
        currentStep = NewsMainNavigatorStep.CONTENT
        router.newRootScreen(RouterConstants.NEWS_MAIN_CONTENT_SCREEN, startPath)
    }

    override fun navigateToContent() {
        currentStep = NewsMainNavigatorStep.CONTENT
        router.newRootScreen(RouterConstants.NEWS_MAIN_CONTENT_SCREEN)
    }

    override fun navigateToSections() {
        currentStep = NewsMainNavigatorStep.SECTIONS
        router.newRootScreen(RouterConstants.NEWS_MAIN_SECTIONS_SCREEN)
    }

    override fun openUrl(url: String) {
        router.openInBrowser(url)
    }
}