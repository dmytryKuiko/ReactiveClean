package com.example.dimi.reactiveclean

import com.example.dimi.reactiveclean.data.splash.SplashReactiveStore
import io.reactivex.Completable
import io.reactivex.Single


class SplashReactiveStoreStubImpl : SplashReactiveStore {

    override fun isFirstLaunch(): Single<Boolean> = Single.just(true)

    override fun setFirstLaunch(): Completable = Completable.complete()
}

