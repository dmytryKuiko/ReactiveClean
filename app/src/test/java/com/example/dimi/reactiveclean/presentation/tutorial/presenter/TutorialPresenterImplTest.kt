package com.example.dimi.reactiveclean.presentation.tutorial.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.dimi.reactiveclean.models.tutorial.ImageType
import com.example.dimi.reactiveclean.navigation.tutorial.TutorialNavigator
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

class TutorialPresenterImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @get:Rule
    val ruleLiveData = InstantTaskExecutorRule()

    @Mock
    private lateinit var navigator: TutorialNavigator

    @Mock
    private lateinit var observer: Observer<ImageType>

    private lateinit var presenter: TutorialPresenterImpl

    @Test
    fun getData() {
        val imageType = ImageType.SOURCE
        presenter = TutorialPresenterImpl(navigator, imageType)
        presenter.getData().observeForever(observer)

        verify(observer).onChanged(imageType)
    }

    @Test
    fun clickNext() {
        presenter = TutorialPresenterImpl(navigator, ImageType.SOURCE)
        presenter.clickNext()
        verify(navigator, times(1)).navigateNext()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun clickBack() {
        presenter = TutorialPresenterImpl(navigator, ImageType.SOURCE)
        presenter.clickBack()
        verify(navigator, times(1)).navigateBack()
        verifyNoMoreInteractions(navigator)
    }

}