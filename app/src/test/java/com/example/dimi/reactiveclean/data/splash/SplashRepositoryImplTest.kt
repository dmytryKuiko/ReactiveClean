package com.example.dimi.reactiveclean.data.splash

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

class SplashRepositoryImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var store: SplashReactiveStore

    @InjectMocks
    private lateinit var repository: SplashRepositoryImpl

    @Test
    fun isFirstLaunch_True() {
        val result = true
        val testObserver = TestObserver.create<Boolean>()
        given(store.isFirstLaunch()).willReturn(Single.just(result))

        repository.isFirstLaunch()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(result)
        verify(store, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun isFirstLaunch_False() {
        val result = false
        val testObserver = TestObserver.create<Boolean>()
        given(store.isFirstLaunch()).willReturn(Single.just(result))

        repository.isFirstLaunch()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(result)
        verify(store, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun setFirstLaunch_Complete() {
        val result = Completable.complete()
        val testObserver = TestObserver.create<Completable>()
        given(store.setFirstLaunch()).willReturn(result)

        repository.setFirstLaunch()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        verify(store, times(1)).setFirstLaunch()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun setFirstLaunch_Error() {
        val throwable = Throwable("Some Error")
        val error = Completable.error(throwable)
        val testObserver = TestObserver.create<Completable>()
        given(store.setFirstLaunch()).willReturn(error)

        repository.setFirstLaunch()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
        verify(store, times(1)).setFirstLaunch()
        verifyNoMoreInteractions(store)
    }

}