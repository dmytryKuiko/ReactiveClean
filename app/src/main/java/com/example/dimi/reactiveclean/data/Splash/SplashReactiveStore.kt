package com.example.dimi.reactiveclean.data.Splash

import io.reactivex.Completable
import io.reactivex.Single

interface SplashReactiveStore {

    fun isFirstLaunch(): Single<Boolean>

    fun setFirstLaunch(): Completable
}