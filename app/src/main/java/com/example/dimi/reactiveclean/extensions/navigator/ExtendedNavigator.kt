package com.example.dimi.reactiveclean.extensions.navigator

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class ExtendedNavigator(
    private val activity: FragmentActivity,
    containerId: Int
) : SupportAppNavigator(activity, containerId) {

    protected abstract fun createCustomTabsIntent(url: String): CustomTabsIntent?

    override fun applyCommand(command: Command?) {
        if (command is OpenInBrowser) {
            val intent = createCustomTabsIntent(command.url)
            intent?.let {
                intent.launchUrl(activity, Uri.parse(command.url))
            }
        }
        super.applyCommand(command)
    }
}