package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.argumentCaptor
import com.example.dimi.reactiveclean.capture
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.search.SearchModel
import com.example.dimi.reactiveclean.models.section.Section
import com.example.dimi.reactiveclean.models.section.SectionResponse
import com.example.dimi.reactiveclean.models.section.SingleSectionResponse
import com.example.dimi.reactiveclean.any as testAny
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class SectionRepositoryImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var store: SectionStore

    @Mock
    private lateinit var newsApi: ServiceNewsApi

    private val mapper: SectionDataMapper = SectionDataMapper()

    private lateinit var repository: SectionRepositoryImpl

    private val testSubscriber = TestSubscriber.create<List<Section>>()

    @Before
    fun setUp() {
        repository = SectionRepositoryImpl(store, newsApi, mapper)
    }

    @Test
    fun getAllSections_Ok_ListPassed() {
        val expectedList = listOf(
            Section("name1", "webb", "12", "aaa", 1),
            Section("nQWe1", "wed11b", "d12", "1aaa", 2)
        )
        given(store.getAllSections()).willReturn(Flowable.just(expectedList))

        repository.getAllSections()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()
        verify(store, times(1)).getAllSections()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getAllSections_Error() {
        val throwable = Throwable("asd")
        given(store.getAllSections()).willReturn(Flowable.error(throwable))

        repository.getAllSections()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(store, times(1)).getAllSections()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun fetchSections_Ok_CompletedAndSavedInStore() {
        val argumentCaptor: ArgumentCaptor<List<Section>> = argumentCaptor()
        val testObserver = TestObserver.create<Completable>()
        val expectedSections = listOf(
            SingleSectionResponse("12", "dasd", "dasda2", "2e3e"),
            SingleSectionResponse("2", "d1wa", "daa2", "2e311e")
        )
        val expectedResponse = SectionResponse(null, null, expectedSections)
        given(newsApi.getAllSections()).willReturn(Single.just(expectedResponse))

        repository.fetchSections()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
        verify(newsApi, times(1)).getAllSections()
        verify(store, times(1)).storeAll(capture(argumentCaptor))

        for(i in 0 until expectedSections.size) {
            assertThat(argumentCaptor.value[i].name, equalTo(expectedSections[i].id))
            assertThat(argumentCaptor.value[i].webUrl, equalTo(expectedSections[i].webUrl))
            assertThat(argumentCaptor.value[i].webTitle, equalTo(expectedSections[i].webTitle))
            assertThat(argumentCaptor.value[i].apiUrl, equalTo(expectedSections[i].apiUrl))
        }

        verifyNoMoreInteractions(newsApi)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun fetchSections_Error_ErrorPassedAndNotSaved() {
        val testObserver = TestObserver.create<Completable>()
        val throwable = Throwable("Error")
        given(newsApi.getAllSections()).willReturn(Single.error(throwable))

        repository.fetchSections()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verify(newsApi, times(1)).getAllSections()
        verifyNoMoreInteractions(newsApi)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getSpecificSections_Ok_ListReturned() {
        val params = "1123"
        val expectedList = listOf(
            Section("name1", "webb", "12", "aaa", 1),
            Section("nQWe1", "wed11b", "d12", "1aaa", 2)
        )
        given(store.getSpecificSections(anyString())).willReturn(Flowable.just(expectedList))

        repository.getSpecificSections(params)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()
        verify(store, times(1)).getSpecificSections(params)
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getSpecificSections_Error() {
        val params = "123"
        val throwable = Throwable("asd")
        given(store.getSpecificSections(anyString())).willReturn(Flowable.error(throwable))

        repository.getSpecificSections(params)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(store, times(1)).getSpecificSections(params)
        verifyNoMoreInteractions(store)
    }

}