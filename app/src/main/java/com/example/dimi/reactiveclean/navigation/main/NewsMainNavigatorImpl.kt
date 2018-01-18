package com.example.dimi.reactiveclean.navigation.main

import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import com.example.dimi.reactiveclean.models.section.ContentChosen
import com.example.dimi.reactiveclean.models.section.ToolbarData
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
            ContentChosen(
                toolbar = ToolbarData.SectionToolbar(title = sectionTitle),
                url = sectionUrl
            )
        )
    }

    override fun onBackPressed() {
        router.exit()
    }

    override fun navigateToSearch() {
        router.navigateTo(RouterConstants.SEARCH_SCREEN)
    }

    override fun openSearchContent(textTyped: String) {
        router.navigateTo(
            RouterConstants.SEARCH_CHOSEN_SCREEN,
            ContentChosen(
                toolbar = ToolbarData.SearchToolbar(title = textTyped),
                url = "search",
                query = textTyped
            )
        )
    }

    override fun backToMainScreen() {
        router.backTo(null)
    }
}