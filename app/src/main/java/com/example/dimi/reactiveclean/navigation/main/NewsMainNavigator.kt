package com.example.dimi.reactiveclean.navigation.main

interface NewsMainNavigator {

    fun startNavigation()

    fun openContentUrl(url: String)

    fun openChosenSection(sectionUrl: String, sectionTitle: String)

    fun onBackPressed()

    fun navigateToSearch()

    fun openSearchContent(textTyped: String)

    fun backToMainScreen()
}