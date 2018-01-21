package com.example.dimi.reactiveclean.presentation.splash.presenter

import com.example.dimi.reactiveclean.TestSchedulers
import com.example.dimi.reactiveclean.domain.splash.SplashInterractor
import com.example.dimi.reactiveclean.navigation.splash.SplashNavigator
import com.example.dimi.reactiveclean.presentation.splash.view.SplashActivity
import com.example.dimi.reactiveclean.utils.AppSchedulers
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.InjectMocks
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
    lateinit var interractor: SplashInterractor

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
        given(interractor.isFirstLaunch()).willReturn(result)

        presenter = SplashPresenterImpl(navigator, interractor, schedulers)

        verify(interractor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interractor)

        verify(navigator, times(1)).navigateToTutorial()
        Mockito.verifyNoMoreInteractions(navigator)
    }

    @Test
    fun getFirstLaunch_False_NavigateToStart() {
        val result = Single.just(false)
        given(interractor.isFirstLaunch()).willReturn(result)

        presenter = SplashPresenterImpl(navigator, interractor, schedulers)

        verify(interractor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interractor)

        verify(navigator, times(1)).navigateToStart()
        Mockito.verifyNoMoreInteractions(navigator)
    }

    @Test(expected = IllegalStateException::class)
    fun getFirstLaunch_Error() {
        val throwable = Throwable("Some")
        val error = Single.error<Boolean>(throwable)
        given(interractor.isFirstLaunch()).willReturn(error)

        presenter = SplashPresenterImpl(navigator, interractor, schedulers)

        verify(interractor, times(1)).isFirstLaunch()
        verifyNoMoreInteractions(interractor)
        verifyNoMoreInteractions(navigator)
    }
}