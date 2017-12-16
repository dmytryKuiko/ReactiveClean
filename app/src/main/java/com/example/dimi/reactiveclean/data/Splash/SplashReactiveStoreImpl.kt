package com.example.dimi.reactiveclean.data.Splash

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SplashReactiveStoreImpl
@Inject
constructor(private val prefs: SharedPreferences) : SplashReactiveStore {
    override fun isFirstLaunch(): Single<Boolean> =
            Single.just<Boolean>(prefs.getBoolean("FirstLaunch", true))

    override fun setFirstLaunch(): Completable =
            Completable.fromCallable { prefs.edit().putBoolean("FirstLaunch", false).apply() }
}