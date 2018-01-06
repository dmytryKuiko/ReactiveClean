package com.example.dimi.reactiveclean.navigation.NewsMain

interface NewsMainNavigator {
    fun startNavigation()

    fun getCurrentStep(): NewsMainNavigatorStep

    fun navigateToContent()

    fun navigateToSections()

    fun openUrl(url: String)
}