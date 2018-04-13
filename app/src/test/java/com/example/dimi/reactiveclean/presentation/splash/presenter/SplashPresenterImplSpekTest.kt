package com.example.dimi.reactiveclean.presentation.splash.presenter

import com.example.dimi.reactiveclean.SplashInteractorStubImpl
import com.example.dimi.reactiveclean.TestSchedulers
import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.spekmock
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

object SplashPresenterImplSpekTest : Spek({
    context("Testing with Mock Object") {
        val navigator: SplashNavigator by spekmock()
        val interactor: SplashInteractor by spekmock()
        val schedulers: SchedulersProvider by memoized { TestSchedulers() }

        on("getFirstLaunch returns true") {
            given(interactor.isFirstLaunch()).willReturn(Single.just(true))
            val presenter: SplashPresenter =
                SplashPresenterImpl(navigator, interactor, schedulers)

            it("verify interaction for SplashInteractor") {
                verify(interactor, times(1)).isFirstLaunch()
            }
            it("no more interactions for SplashInteractor") {
                verifyNoMoreInteractions(interactor)
            }
            it("navigate to Tutorial") {
                verify(navigator, times(1)).navigateToTutorial()
            }
            it("no more interactions for SplashNavigator") {
                verifyNoMoreInteractions(navigator)
            }
        }

        on("getFirstLaunch returns false") {
            given(interactor.isFirstLaunch()).willReturn(Single.just(false))
            val presenter: SplashPresenter =
                SplashPresenterImpl(navigator, interactor, schedulers)

            it("verify interaction for SplashInteractor") {
                verify(interactor, times(1)).isFirstLaunch()
            }
            it("no more interactions for SplashInteractor") {
                verifyNoMoreInteractions(interactor)
            }
            it("navigate to Main") {
                verify(navigator, times(1)).navigateToMain()
            }
            it("no more interactions for SplashNavigator") {
                verifyNoMoreInteractions(navigator)
            }
        }

        on("getFirstLaunch returns error") {
            given(interactor.isFirstLaunch()).willReturn(Single.error(Throwable("Some Error")))
            val presenter: SplashPresenter = SplashPresenterImpl(navigator, interactor, schedulers)
            it("verify interaction for SplashInteractor") {
                verify(interactor, times(1)).isFirstLaunch()
            }
            it("no more interactions for SplashInteractor") {
                verifyNoMoreInteractions(interactor)
            }
            it("navigate to Tutorial") {
                verify(navigator, times(1)).navigateToTutorial()
            }
            it("no more interactions for SplashNavigator") {
                verifyNoMoreInteractions(navigator)
            }
        }
    }

    context("Testing With Stub Object") {
        val navigator: SplashNavigator by spekmock()
        val interactor: SplashInteractor = SplashInteractorStubImpl()
        val schedulers: SchedulersProvider by memoized { TestSchedulers() }

        on("getFirstLaunch returns true") {
            val presenter: SplashPresenter =
                SplashPresenterImpl(navigator, interactor, schedulers)

            it("navigate to Tutorial") {
                verify(navigator, times(1)).navigateToTutorial()
            }
            it("no more interactions for SplashNavigator") {
                verifyNoMoreInteractions(navigator)
            }
        }
    }
})