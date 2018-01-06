package com.example.dimi.reactiveclean.navigation.extended

import ru.terrakok.cicerone.Router

class ExtendedRouter : Router() {

    fun openInBrowser(url: String) {
        executeCommand(OpenInBrowser(url))
    }
}