package com.example.dimi.reactiveclean.Navigator.Main

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.example.dimi.reactiveclean.presentation.Tutorial.view.RouterConstants
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialEverythingFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialFavouritesFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialSourceFragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator


class NavigatorBuilder {

    fun buildSupportAppNavigator(activity: FragmentActivity, containerId: Int,
                                 screenKeyActivity: String? = null, dataActivity: Any? = null,
                                 screenKeyFragment: String? = null, dataFragment: Any? = null): SupportAppNavigator {
        return object : SupportAppNavigator(activity, containerId) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    fun buildSupportFragmentNavigator(manager: FragmentManager, containerId: Int,
                                      activity: Activity): SupportFragmentNavigator {
        return object : SupportFragmentNavigator(manager, containerId) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment =
                    when (screenKey) {
                        RouterConstants.TUTORIAL_SOURCE_SCREEN -> TutorialSourceFragment()
                        RouterConstants.TUTORIAL_EVERYTHING_SCREEN -> TutorialEverythingFragment()
                        RouterConstants.TUTORIAL_FAVOURITES_SCREEN -> TutorialFavouritesFragment()
                        else -> TODO()
                    }

            override fun exit() {
                activity.finish()
            }

            override fun showSystemMessage(message: String?) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG ).show()
            }
        }
    }
}