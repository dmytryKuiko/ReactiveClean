package com.example.dimi.reactiveclean.data.splash

import com.example.dimi.reactiveclean.spekmock
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions


object SplashRepositoryImplSpekTest : Spek( {

    describe("test isFirstLaunch") {
        val testObserver by memoized { TestObserver.create<Boolean>()  }
        val store: SplashReactiveStore by spekmock()
        val repository: SplashRepositoryImpl by memoized { SplashRepositoryImpl(store) }
        on("isFirstLaunch returns the same value") {
            val result = true
            given(store.isFirstLaunch()).willReturn(Single.just(result))

            repository.isFirstLaunch()
                .subscribe(testObserver)
            testObserver.awaitTerminalEvent()

            it("result should be true") {
                testObserver.assertValue(result)
            }

            it("isFirstLaunch was called only one time") {
                verify(store, Mockito.times(1)).isFirstLaunch()
            }

            it("noMore interractions for store") {
                verifyNoMoreInteractions(store)
            }
        }

        on("isFirstLaunch returns error") {
            val throwable = Throwable("Some Error")
            val error = Single.error<Boolean>(throwable)
            given(store.isFirstLaunch()).willReturn(error)

            repository.isFirstLaunch()
                .subscribe(testObserver)
            testObserver.awaitTerminalEvent()

            it("error should be returned") {
                testObserver.assertError(throwable)
            }

            it("isFirstLaunch was called only one time") {
                verify(store, Mockito.times(1)).isFirstLaunch()
            }

            it("noMore interractions for store") {
                verifyNoMoreInteractions(store)
            }
        }
    }
    describe("test setFirstLaunch") {
        val testObserver by memoized { TestObserver.create<Completable>() }
        val store: SplashReactiveStore by spekmock()
        val repository: SplashRepositoryImpl by memoized { SplashRepositoryImpl(store) }
        on("setFirstLaunch completes") {
            val result = Completable.complete()
            given(store.setFirstLaunch()).willReturn(result)

            repository.setFirstLaunch()
                .subscribe(testObserver)
            testObserver.awaitTerminalEvent()

            it("assert Complete") {
                testObserver.assertComplete()
            }

            it("setFirstLaunch was called only one time") {
                verify(store, Mockito.times(1)).setFirstLaunch()
            }

            it("no more interactions for store") {
                verifyNoMoreInteractions(store)
            }
        }

        on("setFirstLaunch returns error") {
            val throwable = Throwable("Some Error")
            val error = Completable.error(throwable)
            given(store.setFirstLaunch()).willReturn(error)

            repository.setFirstLaunch()
                .subscribe(testObserver)
            testObserver.awaitTerminalEvent()

            it("error should be returned") {
                testObserver.assertError(throwable)
            }

            it("setFirstLaunch was called only one time") {
                verify(store, Mockito.times(1)).setFirstLaunch()
            }

            it("no more interactions for store") {
                verifyNoMoreInteractions(store)
            }
        }
    }
})
