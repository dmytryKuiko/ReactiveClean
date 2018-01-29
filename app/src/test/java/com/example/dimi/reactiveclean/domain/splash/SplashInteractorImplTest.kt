package com.example.dimi.reactiveclean.domain.splash

import com.example.dimi.reactiveclean.data.splash.SplashRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class SplashInteractorImplTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: SplashRepository

    @InjectMocks
    private lateinit var interactor: SplashInteractorImpl

    private lateinit var testObserver: TestObserver<Boolean>

    @Before
    fun setUp() {
        testObserver = TestObserver.create()
    }

    @Test
    fun isFirstLaunch_Yes_SetFirstLaunch() {
        val result = true
        given(repository.isFirstLaunch()).willReturn(Single.just(result))
        given(repository.setFirstLaunch()).willReturn(Completable.complete())

        interactor.isFirstLaunch()
            .subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(result)
        verify(repository, times(1)).isFirstLaunch()
        verify(repository, times(1)).setFirstLaunch()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun isFirstLaunch_No_DoNothing() {
        val result = false
        given(repository.isFirstLaunch()).willReturn(Single.just(result))

        interactor.isFirstLaunch()
            .subscribe(testObserver)

        testObserver.assertValue(result)
        verify(repository, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun isFirstLaunch_Error() {
        val throwable = Throwable("Error")
        val error = Single.error<Boolean>(throwable)
        given(repository.isFirstLaunch()).willReturn(error)

        interactor.isFirstLaunch()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)

        verify(repository, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(repository)
    }
}