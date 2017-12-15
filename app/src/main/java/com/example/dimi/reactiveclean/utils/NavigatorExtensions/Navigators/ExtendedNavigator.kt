package com.example.dimi.reactiveclean.utils.NavigatorExtensions.Navigators

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.example.dimi.reactiveclean.Navigator.Tutorial.RouterConstants
import com.example.dimi.reactiveclean.presentation.Main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialEverythingFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialFavouritesFragment
import com.example.dimi.reactiveclean.presentation.Tutorial.view.TutorialSourceFragment
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Commands.ForwardActivity
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Commands.ReplaceActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class ExtendedNavigator(private val activity: FragmentActivity, containerId: Int) :
        SupportFragmentNavigator(activity.supportFragmentManager, containerId) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment = when (screenKey) {
        RouterConstants.TUTORIAL_SOURCE_SCREEN -> TutorialSourceFragment()
        RouterConstants.TUTORIAL_EVERYTHING_SCREEN -> TutorialEverythingFragment()
        RouterConstants.TUTORIAL_FAVOURITES_SCREEN -> TutorialFavouritesFragment()
        else -> TODO()
    }

    override fun exit() {
        activity.finish()
    }

    override fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun applyCommand(command: Command?) = when (command) {
        is ForwardActivity -> applyForward(command)
        is ReplaceActivity -> applyReplace(command)
        else -> super.applyCommand(command)
    }

    private fun applyForward(command: ForwardActivity) {
        val activityIntent = createActivityIntent(command.screenKey, command.transitionData)
        val options = createStartActivityOptions(command, activityIntent)
        activity.startActivity(activityIntent, options)
    }

    private fun applyReplace(command: ReplaceActivity) {
        val activityIntent = createActivityIntent(command.screenKey, command.transitionData)
        val options = createStartActivityOptions(command, activityIntent)
        activity.startActivity(activityIntent, options)
        activity.finish()
    }

    private fun createStartActivityOptions(command: Command, activityIntent: Intent): Bundle? {
        //TODO Override when needed
        return null
    }

    private fun createActivityIntent(screenKey: String, data: Any?): Intent = when(screenKey) {
        RouterConstants.MAIN -> Intent(activity, MainActivity::class.java)
        else -> TODO()
    }
}