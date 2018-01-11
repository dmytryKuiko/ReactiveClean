package com.example.dimi.reactiveclean.navigation.main

interface NewsMainNavigator {
    fun startNavigation()

    fun navigateToContent()

    fun navigateToSection()

    fun openContentUrl(url: String)

    fun openChosenSection(sectionUrl: String)
}