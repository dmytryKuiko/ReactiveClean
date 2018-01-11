package com.example.dimi.reactiveclean.data.splash

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashRepositoryImpl
@Inject constructor(private val store: SplashReactiveStore) : SplashRepository {

    override fun isFirstLaunch(): Single<Boolean> = store.isFirstLaunch()

    override fun setFirstLaunch(): Completable = store.setFirstLaunch()
}