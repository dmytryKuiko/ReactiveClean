package com.example.dimi.reactiveclean.navigation.NewsMain

import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers.ExtendedRouter
import javax.inject.Inject

class NewsMainNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : NewsMainNavigator {
    private var currentStep = NewsMainNavigatorStep.NONE

    override fun startNavigation() {
        if (currentStep != NewsMainNavigatorStep.NONE) {
            return
        }
        currentStep = NewsMainNavigatorStep.CONTENT
        router.navigateTo(RouterConstants.NEWS_MAIN_CONTENT_SCREEN)
    }

    override fun getCurrentStep(): NewsMainNavigatorStep = currentStep

    override fun navigateToContent() {
        currentStep = NewsMainNavigatorStep.CONTENT
        router.newRootScreen(RouterConstants.NEWS_MAIN_CONTENT_SCREEN)
    }

    override fun navigateToSections() {
        currentStep = NewsMainNavigatorStep.SECTIONS
        router.newRootScreen(RouterConstants.NEWS_MAIN_SECTIONS_SCREEN)
    }
}