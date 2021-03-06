package com.example.dimi.reactiveclean.navigation.tutorial

import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.extensions.navigator.ExtendedRouter
import javax.inject.Inject

class TutorialMainNavigatorImpl
@Inject constructor(private val router: ExtendedRouter) : TutorialMainNavigator {

    private var currentStep = TutorialNavigatorStep.NONE

    val firstPageTutorial = object : TutorialNavigator {
        override fun navigateNext() {
            currentStep = TutorialNavigatorStep.SECOND
            router.navigateTo(RouterConstants.TUTORIAL_SECOND_SCREEN)
        }

        override fun navigateBack() {
            router.finishChain()
        }
    }

    val secondPageTutorial = object : TutorialNavigator {
        override fun navigateNext() {
            currentStep = TutorialNavigatorStep.THIRD
            router.navigateTo(RouterConstants.TUTORIAL_THIRD_SCREEN)
        }

        override fun navigateBack() {
            currentStep = TutorialNavigatorStep.FIRST
            router.backTo(RouterConstants.TUTORIAL_FIRST_SCREEN)
        }
    }

    val thirdPageTutorial = object : TutorialNavigator {
        override fun navigateNext() {
            router.newRootScreen(RouterConstants.NEWS_MAIN_ACTIVITY)
        }

        override fun navigateBack() {
            currentStep = TutorialNavigatorStep.SECOND
            router.backTo(RouterConstants.TUTORIAL_SECOND_SCREEN)
        }
    }

    override fun startNavigation() {
        if (currentStep != TutorialNavigatorStep.NONE) {
            return
        }
        currentStep = TutorialNavigatorStep.FIRST
        router.navigateTo(RouterConstants.TUTORIAL_FIRST_SCREEN)
    }
}