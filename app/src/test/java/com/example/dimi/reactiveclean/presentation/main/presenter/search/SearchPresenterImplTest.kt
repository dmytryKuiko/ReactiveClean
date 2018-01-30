package com.example.dimi.reactiveclean.presentation.main.presenter.search

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.view.inputmethod.EditorInfo
import com.example.dimi.reactiveclean.TestSchedulers
import com.example.dimi.reactiveclean.domain.main.search.SearchDomainMapperDB
import com.example.dimi.reactiveclean.domain.main.search.SearchInteractor
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.mock
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.models.search.SearchModel
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.sql.Timestamp
import java.util.concurrent.TimeUnit

class SearchPresenterImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @get:Rule
    val ruleLiveData: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: SearchInteractor

    @Mock
    private lateinit var navigator: NewsMainNavigator

    private lateinit var presenter: SearchPresenterImpl

    private val mapper: SearchDomainMapperDB = SearchDomainMapperDB()

    private val schedulers: TestSchedulers = TestSchedulers()

    @Before
    fun setUp() {
        given(interactor.getSearches()).willReturn(Flowable.empty())
        presenter = SearchPresenterImpl(interactor, navigator, mapper, schedulers)
    }

    @Test
    fun previousSearchClicked_NavigatorOpenContentInvoked() {
        val expectedText = "asdf"
        presenter.previousSearchClicked(SearchDisplayable.Search(expectedText, "asdf"))

        verify(navigator, times(1)).openSearchContent(expectedText)
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun listenEditText_Result_getDataEmits() {
        val testObserver = TestObserver.create<List<SearchModel>>()
        val expectedText = "1234qwer"
        val expectedDateTime = "01-01-1970 01:00:12" // for 12341
        val expectedList = listOf( SearchModel(expectedText, Timestamp(12341)))
        val observable = Observable.just("111")
        val observable2 = Observable.just(expectedList)
        given(interactor.searchTyped(com.example.dimi.reactiveclean.any())).willReturn(observable2)

        presenter.listenEditText(observable)
        testObserver.awaitTerminalEvent(500, TimeUnit.MILLISECONDS)

        val result = presenter.getData().value ?: throw NullPointerException()
        assertThat(result, hasSize(1))
        assertThat(result[0].text, equalTo(expectedText))
        assertThat(result[0].dateTime, equalTo(expectedDateTime))
    }

    @Test
    fun listenEditTextAction_ImeActionDone_SavesSearchAndOpenIt() {
        val testObserver = TestObserver.create<EditTextBindingModel>()
        val expectedModel = EditTextBindingModel("123", EditorInfo.IME_ACTION_DONE)
        val observable = Observable.just(expectedModel)
        given(interactor.actionKeyboardTyped(observable)).willReturn(observable)

        presenter.listenEditTextAction(observable)
        testObserver.await(1000, TimeUnit.MILLISECONDS)

        verify(interactor, times(1)).actionKeyboardTyped(observable)
        verify(interactor, times(1)).storeSearch(expectedModel.text)
        verify(navigator, times(1)).openSearchContent(expectedModel.text)
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun listenEditTextAction_ImeActionNotDone_DoesNothing() {
        val testObserver = TestObserver.create<EditTextBindingModel>()
        val expectedModel = EditTextBindingModel("123", EditorInfo.IME_ACTION_NEXT)
        val observable = Observable.just(expectedModel)
        given(interactor.actionKeyboardTyped(observable)).willReturn(Observable.empty())

        presenter.listenEditTextAction(observable)
        testObserver.await(1000, TimeUnit.MILLISECONDS)

        verify(interactor, times(1)).actionKeyboardTyped(observable)
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun onBackPressed_NavigatorOnBackInvoked() {
        presenter.onBackPressed()

        verify(navigator, times(1)).onBackPressed()
        verifyNoMoreInteractions(navigator)
    }
}