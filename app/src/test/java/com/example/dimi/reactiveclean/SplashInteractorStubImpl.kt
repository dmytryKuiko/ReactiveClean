package com.example.dimi.reactiveclean

import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import io.reactivex.Single


class SplashInteractorStubImpl : SplashInteractor {
    override fun isFirstLaunch(): Single<Boolean> = Single.just(true)
}