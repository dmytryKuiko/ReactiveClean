package com.example.dimi.reactiveclean.presentation.splash.presenter

import com.example.dimi.reactiveclean.TestSchedulers
import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit

class SplashPresenterImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    lateinit var navigator: SplashNavigator

    @Mock
    lateinit var interactor: SplashInteractor

    private val schedulers: SchedulersProvider = TestSchedulers()

    private lateinit var presenter: SplashPresenter

    private lateinit var testObserver: TestObserver<Boolean>

    @Before
    fun setUp() {
        testObserver = TestObserver.create()
    }

    @Test
    fun getFirstLaunch_True_NavigateToTutorial() {
        val result = Single.just(true)
        given(interactor.isFirstLaunch()).willReturn(result)

        presenter = SplashPresenterImpl(navigator, interactor, schedulers)
        testObserver.await(500, TimeUnit.MILLISECONDS)

        verify(interactor, times(1)).isFirstLaunch()

        verifyNoMoreInteractions(interactor)
        verify(navigator, times(1)).navigateToTutorial()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun getFirstLaunch_False_NavigateToStart() {
        val result = Single.just(false)
        given(interactor.isFirstLaunch()).willReturn(result)

        presenter = SplashPresenterImpl(navigator, interactor, schedulers)
        testObserver.await(500, TimeUnit.MILLISECONDS)

        verify(interactor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interactor)

        verify(navigator, times(1)).navigateToStart()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun getFirstLaunch_Error_NavigateToTutorial() {
        val throwable = Throwable("Some")
        given(interactor.isFirstLaunch()).willReturn(Single.error(throwable))

        presenter = SplashPresenterImpl(navigator, interactor, schedulers)
        testObserver.await(1000, TimeUnit.MILLISECONDS)

        verify(interactor, times(1)).isFirstLaunch()
        verify(navigator, times(1)).navigateToTutorial()
        verifyNoMoreInteractions(interactor)
        verifyNoMoreInteractions(navigator)
    }
}