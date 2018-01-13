package com.example.dimi.reactiveclean.extensions.navigator

import ru.terrakok.cicerone.Router

class ExtendedRouter : Router() {

    fun openInBrowser(url: String) {
        executeCommands(OpenInBrowser(url))
    }
}