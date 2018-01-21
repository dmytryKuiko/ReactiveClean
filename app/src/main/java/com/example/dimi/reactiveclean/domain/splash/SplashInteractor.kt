package com.example.dimi.reactiveclean.domain.splash

import io.reactivex.Single

interface SplashInteractor {

    fun isFirstLaunch(): Single<Boolean>
}