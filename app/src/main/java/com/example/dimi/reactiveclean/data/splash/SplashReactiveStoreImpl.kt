package com.example.dimi.reactiveclean.data.splash

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashReactiveStoreImpl
@Inject constructor(private val prefs: SharedPreferences) : SplashReactiveStore {

    /**
     * Determines whether the app is launched at first time or not
     * @return result regarding a first launch
     */
    override fun isFirstLaunch(): Single<Boolean> =
        Single.fromCallable({ prefs.getBoolean("FirstLaunch", true) })

    /**
     * Sets that first launch has happened
     * @return completable that indicates a successful invocation
     */
    override fun setFirstLaunch(): Completable =
        Completable.fromCallable { prefs.edit().putBoolean("FirstLaunch", false).apply() }
}