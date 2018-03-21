package com.example.dimi.reactiveclean.data.splash

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SplashReactiveStoreImpl
@Inject constructor(private val prefs: SharedPreferences) : SplashReactiveStore {

    /**
     * Determines whether app is launched in the first time or not
     * @return result for first Launch
     */
    override fun isFirstLaunch(): Single<Boolean> =
        Single.fromCallable({ prefs.getBoolean("FirstLaunch", true) })

    /**
     * Sets that first launch happened
     * @return completable is invokation was successful
     */
    override fun setFirstLaunch(): Completable =
        Completable.fromCallable { prefs.edit().putBoolean("FirstLaunch", false).apply() }
}