package com.example.dimi.reactiveclean.data.splash

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashRepositoryImpl
@Inject constructor(private val store: SplashReactiveStore) : SplashRepository {

    /**
     * Checks the first launch
     * @return result for the first launch
     */
    override fun isFirstLaunch(): Single<Boolean> = store.isFirstLaunch()

    /**
     * Sets that the app was opened at first time
     * @return completable whether an operation was successful or not
     */
    override fun setFirstLaunch(): Completable = store.setFirstLaunch()
}