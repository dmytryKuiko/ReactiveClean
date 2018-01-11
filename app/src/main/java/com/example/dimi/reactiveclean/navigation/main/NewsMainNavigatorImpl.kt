package com.example.dimi.reactiveclean.navigation.main

import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import com.example.dimi.reactiveclean.navigation.RouterConstants
import javax.inject.Inject

class NewsMainNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : NewsMainNavigator {

    private var currentStep = NewsMainNavigatorStep.NONE

    override fun startNavigation() {
        if (currentStep != NewsMainNavigatorStep.NONE) {
            return
        }
        currentStep = NewsMainNavigatorStep.CONTENT
        router.newRootScreen(RouterConstants.CONTENT_SCREEN)
    }

    override fun navigateToContent() {
        currentStep = NewsMainNavigatorStep.CONTENT
        router.newRootScreen(RouterConstants.CONTENT_SCREEN)
    }

    override fun navigateToSection() {
        currentStep = NewsMainNavigatorStep.SECTION
        router.newRootScreen(RouterConstants.SECTION_SCREEN)
    }

    override fun openContentUrl(url: String) {
        router.openInBrowser(url)
    }

    override fun openChosenSection(sectionUrl: String) {
        router.navigateTo(RouterConstants.SECTION_CHOSEN_SCREEN, sectionUrl)
    }
}