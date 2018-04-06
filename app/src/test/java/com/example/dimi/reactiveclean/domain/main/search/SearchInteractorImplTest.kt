package com.example.dimi.reactiveclean.domain.main.search

import android.view.inputmethod.EditorInfo
import com.example.dimi.reactiveclean.argumentCaptor
import com.example.dimi.reactiveclean.capture
import com.example.dimi.reactiveclean.data.main.search.SearchRepository
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
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
    fun searchTyped_DbEmpty_EmitsEmpty() {
        val testObserver = TestObserver.create<List<SearchModel>>()
        given(repository.getSearches(anyInt())).willReturn(Flowable.just(emptyList()))

        interactor.listenSymbolTyped(Observable.just("123"))
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(emptyList())
            .assertNoErrors()
        verify(repository, times(1)).getSearches(25)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun searchTyped_DbHasSearches_EmitsSearchModel() {
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
        given(repository.getSearches(anyInt())).willReturn(Flowable.just(returnedList))

        interactor.listenSymbolTyped(Observable.just(expectedName1))
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        val resultList = testObserver.values()[0]
        assertThat(resultList, hasSize(1))
        assertThat(resultList[0].text, equalTo(expectedName1))
        assertThat(resultList[0].dateTime.time, equalTo(expectedTime1))
    }

//    @Test
//    fun actionKeyboardTyped_EmitsOnlyWithActionDone() {
//        val testObserver = TestObserver.create<EditTextBindingModel>()
//        val expectedModel = EditTextBindingModel("blabla", EditorInfo.IME_ACTION_DONE)
//        val expectedModelNo = EditTextBindingModel("blabla222", EditorInfo.IME_ACTION_GO)
//        val observable = Observable.just(expectedModel, expectedModelNo)
//
//        interactor.listenActionDone(observable)
//            .subscribe(testObserver)
//        testObserver.awaitTerminalEvent()
//
//        testObserver.assertValueCount(1)
//            .assertValue(expectedModel)
//            .assertNoErrors()
//        verifyNoMoreInteractions(repository)
//    }
}