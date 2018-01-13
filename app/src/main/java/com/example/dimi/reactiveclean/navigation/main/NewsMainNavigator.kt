package com.example.dimi.reactiveclean.navigation.main

interface NewsMainNavigator {

    fun startNavigation()

    fun openContentUrl(url: String)

    fun openChosenSection(sectionUrl: String)

    fun onBackPressed()
}