package com.example.dimi.reactiveclean.data.splash

import io.reactivex.Completable
import io.reactivex.Single

interface SplashRepository {

    fun isFirstLaunch(): Single<Boolean>

    fun setFirstLaunch(): Completable
}