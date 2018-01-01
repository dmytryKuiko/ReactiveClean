package com.example.dimi.reactiveclean.presentation.Splash.presenter

import com.example.dimi.reactiveclean.navigation.Splash.SplashNavigator
import com.example.dimi.reactiveclean.domain.Splash.SplashInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenterImpl
@Inject
constructor(private val navigator: SplashNavigator,
            private val interractor: SplashInterractor,
            private val schedulers: SchedulersProvider) : SplashPresenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        interractor.isFirstLaunch()
                .subscribeOn(schedulers.computation())
                .observeOn(schedulers.ui())
                .subscribe(this::handleResult, this::handleError)
                .addTo(compositeDisposable)
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