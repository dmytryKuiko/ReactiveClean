package com.example.dimi.reactiveclean.data.splash

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashRepositoryImpl
@Inject constructor(private val store: SplashReactiveStore) : SplashRepository {

    /**
     * Checks first launch
     * @return result for first launch
     */
    override fun isFirstLaunch(): Single<Boolean> = store.isFirstLaunch()

    /**
     * Sets that app that first launch has happened
     * @return completable whether operation was successful or not
     */
    override fun setFirstLaunch(): Completable = store.setFirstLaunch()
}