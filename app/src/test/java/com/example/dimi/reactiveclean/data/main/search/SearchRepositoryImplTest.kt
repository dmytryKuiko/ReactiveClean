package com.example.dimi.reactiveclean.data.main.search

import com.example.dimi.reactiveclean.data.main.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.SingleContentResponse
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import com.example.dimi.reactiveclean.extensions.*
import com.example.dimi.reactiveclean.mock
import io.reactivex.observers.TestObserver
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import java.sql.Timestamp

class SearchRepositoryImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var store: SearchStore

    @Mock
    private lateinit var newsApi: ServiceNewsApi

    private val mapper: ContentDataMapperForDB = ContentDataMapperForDB()

    private lateinit var repository: SearchRepositoryImpl

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(store, newsApi, mapper)
    }

    @Test
    fun storeSearch_MethodInvoked() {
        val expectedText = "Hallo"
        val expectedTimestamp: Timestamp = Timestamp(12345)
        val expectedId = 123
        val searchModel = SearchModel(expectedText, expectedTimestamp, expectedId)

        repository.storeSearch(searchModel)

        verify(store, times(1)).store(searchModel)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getSearches_Ok_ResultExpected() {
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
        given(store.getSearches(20)).willReturn(Flowable.just(expectedList))

        store.getSearches(20)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()

        verify(store, times(1)).getSearches(20)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getSearches_Error_ErrorPassed() {
        val testSubscriber = TestSubscriber.create<List<SearchModel>>()
        val throwable = Throwable("Error")
        given(store.getSearches(20)).willReturn(Flowable.error(throwable))

        store.getSearches(20)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()

        verify(store, times(1)).getSearches(20)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getResultsForSearch_Ok_ResultExpected() {
        val testObserver = TestObserver.create<List<Content>>()
        val expectedId = "12"
        val expectedType = "123"
        val expectedSectionId = "qwer"
        val expectedSectionName = "sec"
        val expectedPublicationDate = "2018-01-28T15:23:14Z"
        val expectedWebTitle = "title:123"
        val expectedWebUrl = "http:123"
        val expectedApiUrl = "htdsadastp:123"
        val expectedPillarName = "pilllaaarr"
        val expectedContent = ContentResponse(
            null, null, null, null,
            null, null, null, listOf(
                SingleContentResponse(
                    expectedId, expectedType, expectedSectionId, expectedSectionName,
                    expectedPublicationDate, expectedWebTitle,
                    expectedWebUrl, expectedApiUrl, expectedPillarName
                )
            )
        )
        given(newsApi.getSearchContent(anyString())).willReturn(Single.just(expectedContent))


        repository.getResultsForSearch("some")
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertNoErrors()

        val content = testObserver.values()[0][0]
        assertThat(content.name, equalTo(expectedWebTitle))
        assertThat(content.type, equalTo(expectedType))
        assertThat(content.sectionName, equalTo(expectedSectionName))
        assertThat(content.webUrl, equalTo(expectedWebUrl))
        assertThat(content.pillarName, equalTo(expectedPillarName))

        verify(newsApi, times(1)).getSearchContent("some")
        verifyNoMoreInteractions(newsApi)
    }

    @Test
    fun getResultsForSearch_Error_ErrorPassed() {
        val testObserver = TestObserver.create<List<Content>>()

        val throwable = Throwable("Error")
        given(newsApi.getSearchContent(anyString())).willReturn(Single.error(throwable))


        repository.getResultsForSearch("some")
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()

        verify(newsApi, times(1)).getSearchContent("some")
        verifyNoMoreInteractions(newsApi)
    }
}