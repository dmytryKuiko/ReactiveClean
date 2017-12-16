package com.example.dimi.reactiveclean.navigation.Splash

import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers.ExtendedRouter
import javax.inject.Inject

class SplashNavigatorImpl
@Inject
constructor(val router: ExtendedRouter) : SplashNavigator {
    override fun navigateToTutorial() {
        router.navigateToActivity(RouterConstants.TUTORIAL_ACTIVITY)
    }

    override fun navigateToStart() {
        router.navigateToActivity(RouterConstants.MAIN_ACTIVITY)
    }
}