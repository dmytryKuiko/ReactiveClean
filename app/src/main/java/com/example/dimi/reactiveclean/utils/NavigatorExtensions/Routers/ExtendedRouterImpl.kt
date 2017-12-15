package com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers

import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Commands.ForwardActivity
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Commands.ReplaceActivity

class ExtendedRouterImpl : ExtendedRouter() {
    override fun navigateToActivity(screenKey: String) {
        navigateToActivity(screenKey, null)
    }

    override fun navigateToActivity(screenKey: String, data: Any?) {
        executeCommand(ForwardActivity(screenKey, data))
    }

    override fun replaceActivity(screenKey: String) {
        replaceActivity(screenKey, null)
    }

    override fun replaceActivity(screenKey: String, data: Any?) {
        executeCommand(ReplaceActivity(screenKey, data))
    }
}