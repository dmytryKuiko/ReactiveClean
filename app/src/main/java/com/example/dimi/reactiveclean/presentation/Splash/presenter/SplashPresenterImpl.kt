package com.example.dimi.reactiveclean.presentation.Splash.presenter

import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigator
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenterImpl
@Inject
constructor(private val navigator: SplashNavigator,
            private val interractor: SplashInterractor) : SplashPresenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        val disposable = interractor.isFirstLaunch()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError)
        compositeDisposable.add(disposable)
    }

    override fun disposeSubscriptions() = compositeDisposable.clear()

    private fun handleResult(value: Boolean) {
        when (value) {
            true -> navigator.navigateToTutorial()
            else -> navigator.navigateToStart()
        }
    }

    private fun handleError(throwable: Throwable) {
        throw IllegalStateException("Wrong in SharedPrefs")
    }
}