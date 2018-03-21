package com.example.dimi.reactiveclean.presentation.splash.presenter

import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenterImpl
@Inject constructor(
    private val navigator: SplashNavigator,
    private val interactor: SplashInteractor,
    private val schedulers: SchedulersProvider
) : SplashPresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        interactor.isFirstLaunch()
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())
            .subscribe(this::handleResult, this::handleError)
            .addTo(compositeDisposable)
    }

    override fun disposeSubscriptions() = compositeDisposable.clear()

    private fun handleResult(value: Boolean) {
        when (value) {
            true -> navigator.navigateToTutorial()
            else -> navigator.navigateToMain()
        }
    }

    private fun handleError(throwable: Throwable) {
        navigator.navigateToTutorial()
    }
}