package com.example.dimi.reactiveclean.navigation.Splash

import com.example.dimi.reactiveclean.navigation.RouterConstants
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SplashNavigatorImpl
@Inject
constructor(private val router: Router) : SplashNavigator {
    override fun navigateToTutorial() {
        router.newRootScreen(RouterConstants.TUTORIAL_ACTIVITY)
    }

    override fun navigateToStart() {
        router.newRootScreen(RouterConstants.NEWS_MAIN_ACTIVITY)
    }
}