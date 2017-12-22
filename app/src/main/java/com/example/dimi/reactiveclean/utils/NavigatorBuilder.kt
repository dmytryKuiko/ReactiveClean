package com.example.dimi.reactiveclean.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.example.dimi.reactiveclean.navigation.RouterConstants
import com.example.dimi.reactiveclean.presentation.Main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialSecondFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialThirdFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialFirstFragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator


class NavigatorBuilder {

    fun buildSupportAppNavigator(activity: FragmentActivity, containerId: Int,
                                 screenKeyActivity: String? = null, dataActivity: Any? = null,
                                 screenKeyFragment: String? = null, dataFragment: Any? = null): SupportAppNavigator {
        return object : SupportAppNavigator(activity, containerId) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent? =
                    when (screenKey) {
                        RouterConstants.MAIN_ACTIVITY -> Intent(activity, MainActivity::class.java)
                        else -> TODO()
                    }

            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    when (screenKey) {
                        RouterConstants.TUTORIAL_FIRST_SCREEN -> TutorialFirstFragment()
                        RouterConstants.TUTORIAL_SECOND_SCREEN -> TutorialSecondFragment()
                        RouterConstants.TUTORIAL_THIRD_SCREEN -> TutorialThirdFragment()
                        else -> TODO()
                    }
        }
    }

    fun buildSupportFragmentNavigator(manager: FragmentManager, containerId: Int, activity: Activity,
                                      actionExit: () -> Unit): SupportFragmentNavigator {
        return object : SupportFragmentNavigator(manager, containerId) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    when (screenKey) {
                        RouterConstants.TUTORIAL_FIRST_SCREEN -> TutorialFirstFragment()
                        RouterConstants.TUTORIAL_SECOND_SCREEN -> TutorialSecondFragment()
                        RouterConstants.TUTORIAL_THIRD_SCREEN -> TutorialThirdFragment()
                        else -> TODO()
                    }

            override fun exit() {
                actionExit
            }

            override fun showSystemMessage(message: String?) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}