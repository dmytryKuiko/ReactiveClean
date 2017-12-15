package com.example.dimi.reactiveclean.Navigator.Tutorial

import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers.ExtendedRouter
import javax.inject.Inject

class TutorialMainNavigatorImpl
@Inject constructor(val router: ExtendedRouter) : TutorialMainNavigator {

    private var currentStepWizard = TutorialNavigatorStep.NONE

    val sourceWizard = object : TutorialNavigator {
        override fun wizardNext() {
            currentStepWizard = TutorialNavigatorStep.TUTORIAL_EVERYTHING
            router.navigateTo(RouterConstants.TUTORIAL_EVERYTHING_SCREEN)
        }

        override fun wizardBack() {
            router.finishChain()
        }
    }

    val everythingWizard = object : TutorialNavigator {
        override fun wizardNext() {
            currentStepWizard = TutorialNavigatorStep.TUTORIAL_FAVOURITES
            router.navigateTo(RouterConstants.TUTORIAL_FAVOURITES_SCREEN)
        }

        override fun wizardBack() {
            currentStepWizard = TutorialNavigatorStep.TUTORIAL_SOURCE
            router.backTo(RouterConstants.TUTORIAL_SOURCE_SCREEN)
        }
    }

    val favouritesWizard = object : TutorialNavigator {
        override fun wizardNext() {
            router.replaceActivity(RouterConstants.MAIN)
           // router.finishChain()
            //router.navigateTo(RouterConstants.MAIN)
        }

        override fun wizardBack() {
            currentStepWizard = TutorialNavigatorStep.TUTORIAL_EVERYTHING
            router.backTo(RouterConstants.TUTORIAL_EVERYTHING_SCREEN)
        }
    }

    override fun startNavigation() {
        if (currentStepWizard != TutorialNavigatorStep.NONE) {
            return
        }
        currentStepWizard = TutorialNavigatorStep.TUTORIAL_SOURCE
        router.navigateTo(RouterConstants.TUTORIAL_SOURCE_SCREEN)
    }
}