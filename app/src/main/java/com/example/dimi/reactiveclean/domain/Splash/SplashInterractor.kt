package com.example.dimi.reactiveclean.domain.Splash

import io.reactivex.Observable
import io.reactivex.Single

interface SplashInterractor {

    fun isFirstLaunch() : Single<Boolean>
}