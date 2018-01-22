package com.example.dimi.reactiveclean.presentation.main.presenter.content

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.dimi.reactiveclean.domain.main.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.main.content.ContentInteractor
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDB
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.mock
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.mockito.Mockito.times


class ContentPresenterImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @get:Rule
    val ruleLiveData: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: ContentInteractor

    @Mock
    private lateinit var paginator: PaginatorDB<ContentDisplayable>

    @Mock
    private lateinit var navigator: NewsMainNavigator

    @Mock
    private lateinit var menuController: MenuController

    private val mapper: ContentDomainMapper = ContentDomainMapper()

    private lateinit var presenter: ContentPresenterImpl

    @Before
    fun setUp() {
        presenter = ContentPresenterImpl(interactor, mapper, paginator, navigator, menuController)
    }

    @Test
    fun getData_ReturnsTheSame() {
        val paginatorModel: PaginatorModelResult<ContentDisplayable> = mock()
        val liveData = MutableLiveData<PaginatorModelResult<ContentDisplayable>>()
        liveData.value = paginatorModel
        val observer: Observer<PaginatorModelResult<ContentDisplayable>> = mock()
        given(paginator.getData()).willReturn(liveData)

        presenter.getData().observeForever(observer)

        verify(observer).onChanged(paginatorModel)
        verify(paginator, times(1)).getData()
    }

    @Test
    fun getSingleEventData_ReturnsTheSame() {
        val expected = "Test"
        val liveData = MutableLiveData<String>()
        liveData.value = expected
        val observer: Observer<String> = mock()
        given(paginator.getSingleEvent()).willReturn(liveData)

        presenter.getSingleEventData().observeForever(observer)

        verify(observer).onChanged(expected)
        verify(paginator, times(1)).getSingleEvent()
    }

    @Test
    fun disposeSubscriptions_InvokesPaginatorDispose() {
        presenter.disposeSubscriptions()

        verify(paginator, times(1)).disposeSubscriptions()
    }

    @Test
    fun refreshClicked_InvokesPaginatorRefresh() {
        presenter.refreshClicked()

        verify(paginator, times(2)).refresh()
    }

    @Test
    fun loadNextContentPage_InvokesPaginatorLodNext() {
        presenter.loadNextContentPage()

        verify(paginator, times(1)).loadNewPage()
    }

    @Test
    fun openCurrentContent_OpensTheSameUrl() {
        val title = "SOmeTitle"
        val url = "URL!"
        val time = "00:12"
        val sectionName = "Football"
        val content = ContentDisplayable.Content(title, url, time, sectionName)

        presenter.openCurrentContent(content)

        verify(navigator, times(1)).openContentUrl(url)
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun openMenuClicked_OpensMenu() {
        presenter.openMenuClicked()

        verify(menuController, times(1)).open()
        verifyNoMoreInteractions(menuController)
    }

    @Test
    fun searchClicked_NavigatesToSearchFragment() {
        presenter.searchClicked()

        verify(navigator, times(1)).navigateToSearch()
        verifyNoMoreInteractions(navigator)
    }

}