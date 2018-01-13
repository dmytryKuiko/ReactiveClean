package com.example.dimi.reactiveclean.navigation.main

import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import com.example.dimi.reactiveclean.models.section.SectionChosenModel
import com.example.dimi.reactiveclean.navigation.RouterConstants
import javax.inject.Inject

class NewsMainNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : NewsMainNavigator {

    override fun startNavigation() {
        router.newRootScreen(RouterConstants.MAIN_SCREEN)
    }

    override fun openContentUrl(url: String) {
        router.openInBrowser(url)
    }

    override fun openChosenSection(sectionUrl: String, sectionTitle: String) {
        router.navigateTo(
                RouterConstants.SECTION_CHOSEN_SCREEN,
                SectionChosenModel(url = sectionUrl, title = sectionTitle)
        )
    }

    override fun onBackPressed() {
        router.exit()
    }
}