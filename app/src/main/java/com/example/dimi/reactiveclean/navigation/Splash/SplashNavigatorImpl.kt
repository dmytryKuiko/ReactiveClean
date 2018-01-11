package com.example.dimi.reactiveclean.navigation.splash

import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import javax.inject.Inject

class SplashNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : SplashNavigator {

    override fun navigateToTutorial() {
        router.newRootScreen(RouterConstants.TUTORIAL_ACTIVITY)
    }

    override fun navigateToStart() {
        router.newRootScreen(RouterConstants.NEWS_MAIN_ACTIVITY)
    }
}