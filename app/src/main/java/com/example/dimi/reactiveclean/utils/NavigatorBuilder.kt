package com.example.dimi.reactiveclean.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.example.dimi.reactiveclean.Navigator.Tutorial.RouterConstants
import com.example.dimi.reactiveclean.presentation.Main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialEverythingFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialFavouritesFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialSourceFragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward


class NavigatorBuilder {

    fun buildSupportAppNavigator(activity: FragmentActivity, containerId: Int,
                                 screenKeyActivity: String? = null, dataActivity: Any? = null,
                                 screenKeyFragment: String? = null, dataFragment: Any? = null): SupportAppNavigator {
        return object : SupportAppNavigator(activity, containerId) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent? =
                    when (screenKey) {
                        RouterConstants.MAIN -> Intent(activity, MainActivity::class.java)
                        else -> TODO()
                    }

            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    when (screenKey) {
                        RouterConstants.TUTORIAL_SOURCE_SCREEN -> TutorialSourceFragment()
                        RouterConstants.TUTORIAL_EVERYTHING_SCREEN -> TutorialEverythingFragment()
                        RouterConstants.TUTORIAL_FAVOURITES_SCREEN -> TutorialFavouritesFragment()
                        else -> TODO()
                    }
        }
    }

    fun buildSupportFragmentNavigator(manager: FragmentManager, containerId: Int, activity: Activity,
                                      actionExit: () -> Unit): SupportFragmentNavigator {
        return object : SupportFragmentNavigator(manager, containerId) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    when (screenKey) {
                        RouterConstants.TUTORIAL_SOURCE_SCREEN -> TutorialSourceFragment()
                        RouterConstants.TUTORIAL_EVERYTHING_SCREEN -> TutorialEverythingFragment()
                        RouterConstants.TUTORIAL_FAVOURITES_SCREEN -> TutorialFavouritesFragment()
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