package com.example.dimi.reactiveclean.presentation.splash.presenter

import com.example.dimi.reactiveclean.TestSchedulers
import com.example.dimi.reactiveclean.domain.splash.SplashInteractor
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

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

        verify(interactor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interactor)

        verify(navigator, times(1)).navigateToTutorial()
        Mockito.verifyNoMoreInteractions(navigator)
    }

    @Test
    fun getFirstLaunch_False_NavigateToStart() {
        val result = Single.just(false)
        given(interactor.isFirstLaunch()).willReturn(result)

        presenter = SplashPresenterImpl(navigator, interactor, schedulers)

        verify(interactor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interactor)

        verify(navigator, times(1)).navigateToStart()
        Mockito.verifyNoMoreInteractions(navigator)
    }

    @Test(expected = IllegalStateException::class)
    fun getFirstLaunch_Error() {
        val throwable = Throwable("Some")
        val error = Single.error<Boolean>(throwable)
        given(interactor.isFirstLaunch()).willReturn(error)

        presenter = SplashPresenterImpl(navigator, interactor, schedulers)

        verify(interactor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interactor)
        verifyNoMoreInteractions(navigator)
    }
}