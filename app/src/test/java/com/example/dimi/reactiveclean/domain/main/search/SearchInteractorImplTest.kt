package com.example.dimi.reactiveclean.domain.main.search

import android.view.inputmethod.EditorInfo
import com.example.dimi.reactiveclean.argumentCaptor
import com.example.dimi.reactiveclean.capture
import com.example.dimi.reactiveclean.data.main.search.SearchRepository
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.sql.Timestamp

class SearchInteractorImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: SearchRepository

    @InjectMocks
    private lateinit var interactor: SearchInteractorImpl

    @Test
    fun storeSearch_ModeltWithTheSameFieldsSaved() {
        val expectedText = "SomeSearch"
        val argumentCaptor: ArgumentCaptor<SearchModel> = argumentCaptor()

        interactor.storeSearch(expectedText)

        verify(repository, times(1)).storeSearch(capture(argumentCaptor))
        verifyNoMoreInteractions(repository)
        assertThat(argumentCaptor.value.text, equalTo(expectedText))
        assertThat(argumentCaptor.value.id, `is`(nullValue()))
    }

    @Test
    fun getSearches_ShowWithDifferentText() {
        val expectedName1 = "one"
        val expectedTime1 = 1L
        val expectedName2 = "two"
        val expectedTime2 = 222L
        val returnedList = listOf(
            SearchModel(expectedName1, Timestamp(expectedTime1)),
            SearchModel(expectedName1, Timestamp(expectedTime1)),
            SearchModel(expectedName1, Timestamp(expectedTime2)),
            SearchModel(expectedName2, Timestamp(expectedTime2))
        )
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        given(repository.getSearches(anyInt())).willReturn(Flowable.just(returnedList))

        interactor.getSearches()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertNoErrors()

        val resultList = testSubscriber.values()[0]
        assertThat(resultList, hasSize(2))
        assertThat(resultList[0].text, equalTo(expectedName1))
        assertThat(resultList[0].dateTime.time, `is`(expectedTime1))
        assertThat(resultList[1].text, equalTo(expectedName2))
        assertThat(resultList[1].dateTime.time, `is`(expectedTime2))

        verify(repository, times(1)).getSearches(25)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getSearches_Error() {
        val throwable = Throwable("dsada")
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        given(repository.getSearches(anyInt())).willReturn(Flowable.error(throwable))

        interactor.getSearches()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(repository, times(1)).getSearches(25)
        verifyNoMoreInteractions(repository)
    }


    @Test
    fun getResultsForSearch_Ok_ResultExpected() {
        val content = Content(
            "aa", "bb", "section",
            1516615203578, "http", "pillar"
        )
        val expectedList = listOf(content)
        val testObserver = TestObserver.create<List<Content>>()
        val expectedText = "asdf"
        given(repository.getResultsForSearch(anyString())).willReturn(Single.just(expectedList))

        interactor.getResultsForSearch(expectedText)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(expectedList)
            .assertNoErrors()
        verify(repository, times(1)).getResultsForSearch(expectedText)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getResultsForSearch_Error() {
        val throwable = Throwable("Error")
        val testObserver = TestObserver.create<List<Content>>()
        val expectedText = "asdf"
        given(repository.getResultsForSearch(anyString())).willReturn(Single.error(throwable))

        interactor.getResultsForSearch(expectedText)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verify(repository, times(1)).getResultsForSearch(expectedText)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun searchTyped_TextAndSavedListEmpty_EmitsEmpty() {
        val testObserver = TestObserver.create<List<SearchModel>>()

        interactor.searchTyped(Observable.just(""))
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(emptyList())
            .assertNoErrors()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun searchTyped_TextNotBlankSavedListEmpty_EmitsEmpty() {
        val testObserver = TestObserver.create<List<SearchModel>>()

        interactor.searchTyped(Observable.just("123"))
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(emptyList())
            .assertNoErrors()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun searchTyped_SavedListHasText_EmitsSearchModel() {
        val testObserver = TestObserver.create<List<SearchModel>>()
        val expectedName1 = "one"
        val expectedTime1 = 1L
        val expectedName2 = "two"
        val expectedTime2 = 222L
        val returnedList = listOf(
            SearchModel(expectedName1, Timestamp(expectedTime1)),
            SearchModel(expectedName1, Timestamp(expectedTime1)),
            SearchModel(expectedName1, Timestamp(expectedTime2)),
            SearchModel(expectedName2, Timestamp(expectedTime2))
        )
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        given(repository.getSearches(anyInt())).willReturn(Flowable.just(returnedList))

        interactor.getSearches()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()


        interactor.searchTyped(Observable.just(expectedName1))
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        val resultList = testObserver.values()[0]
        assertThat(resultList, hasSize(1))
        assertThat(resultList[0].text, equalTo(expectedName1))
        assertThat(resultList[0].dateTime.time, equalTo(expectedTime1))
    }

    @Test
    fun actionKeyboardTyped_EmitsOnlyWithActionDone() {
        val testObserver = TestObserver.create<EditTextBindingModel>()
        val expectedModel = EditTextBindingModel("blabla", EditorInfo.IME_ACTION_DONE)
        val expectedModelNo = EditTextBindingModel("blabla222", EditorInfo.IME_ACTION_GO)
        val observable = Observable.just(expectedModel, expectedModelNo)

        interactor.actionKeyboardTyped(observable)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValueCount(1)
            .assertValue(expectedModel)
            .assertNoErrors()
        verifyNoMoreInteractions(repository)
    }
}