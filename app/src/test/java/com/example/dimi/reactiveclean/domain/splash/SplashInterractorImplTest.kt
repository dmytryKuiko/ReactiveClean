package com.example.dimi.reactiveclean.domain.splash

import com.example.dimi.reactiveclean.data.splash.SplashRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class SplashInterractorImplTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: SplashRepository

    @InjectMocks
    private lateinit var interractor: SplashInterractorImpl

    private lateinit var testObserver: TestObserver<Boolean>

    @Before
    fun setUp() {
        testObserver = TestObserver.create()
    }

    @Test
    fun isFirstLaunch_Yes_SetFirstLaunch() {
        val result = true
        given(repository.isFirstLaunch()).willReturn(Single.just(result))

        interractor.isFirstLaunch()
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

        interractor.isFirstLaunch()
            .subscribe(testObserver)

        testObserver.assertValue(result)
        verify(repository, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(repository)
    }
}