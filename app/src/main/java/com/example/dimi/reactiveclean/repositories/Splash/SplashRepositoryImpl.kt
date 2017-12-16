package com.example.dimi.reactiveclean.repositories.Splash

import com.example.dimi.reactiveclean.data.Splash.SplashReactiveStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashRepositoryImpl
@Inject
constructor(private val store: SplashReactiveStore) : SplashRepository {
    override fun isFirstLaunch(): Single<Boolean> = store.isFirstLaunch()

    override fun setFirstLaunch(): Completable = store.setFirstLaunch()
}