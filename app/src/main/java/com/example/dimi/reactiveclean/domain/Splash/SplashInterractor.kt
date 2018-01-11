package com.example.dimi.reactiveclean.domain.splash

import io.reactivex.Single

interface SplashInterractor {

    fun isFirstLaunch() : Single<Boolean>
}