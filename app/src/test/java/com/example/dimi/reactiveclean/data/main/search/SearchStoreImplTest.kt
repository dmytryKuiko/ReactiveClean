package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.data.db.SearchDao
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.sql.Timestamp

class SearchStoreImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var searchDao: SearchDao

    @InjectMocks
    private lateinit var store: SearchStoreImpl

    @Test
    fun store_MethodInvoked() {
        val expectedText = "Hallo"
        val expectedTimestamp: Timestamp = Timestamp(12345)
        val expectedId = 123
        val searchModel = SearchModel(expectedText, expectedTimestamp, expectedId)

        store.store(searchModel)

        verify(searchDao, times(1)).insert(searchModel)
        verifyNoMoreInteractions(searchDao)
    }

    @Test
    fun getSearches_Ok_ReturnedTheSameResult() {
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        val expectedText1 = "Hallo"
        val expectedTimestamp1: Timestamp = Timestamp(12345)
        val expectedId1 = 123
        val searchModel1 = SearchModel(expectedText1, expectedTimestamp1, expectedId1)

        val expectedText2 = "Hallo"
        val expectedTimestamp2: Timestamp = Timestamp(12345)
        val expectedId2 = 123
        val searchModel2 = SearchModel(expectedText2, expectedTimestamp2, expectedId2)

        val expectedList = listOf(searchModel1, searchModel2)
        given(searchDao.getSearches(20)).willReturn(Flowable.just(expectedList))

        store.getSearches(20)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()

        verify(searchDao, times(1)).getSearches(20)
        verifyNoMoreInteractions(searchDao)
    }

    @Test
    fun getSearches_Error() {
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        val throwable = Throwable("Error")
        given(searchDao.getSearches(20)).willReturn(Flowable.error(throwable))

        store.getSearches(20)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()

        verify(searchDao, times(1)).getSearches(20)
        verifyNoMoreInteractions(searchDao)
    }
}