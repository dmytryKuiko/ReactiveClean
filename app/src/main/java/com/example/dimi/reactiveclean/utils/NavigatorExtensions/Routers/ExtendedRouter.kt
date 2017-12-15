package com.example.dimi.reactiveclean.utils.NavigatorExtensions.Routers

import ru.terrakok.cicerone.Router

abstract class ExtendedRouter : Router() {
    abstract fun navigateToActivity(screenKey: String)

    abstract fun navigateToActivity(screenKey: String, data: Any?)

    abstract fun replaceActivity(screenKey: String)

    abstract fun replaceActivity(screenKey: String, data: Any?)
}