package com.example.dimi.reactiveclean.data.main.sectionChosen

import com.example.dimi.reactiveclean.data.main.content.ContentDataMapperForDB
import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.mock
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.SingleContentResponse
import com.example.dimi.reactiveclean.models.section.ContentChosen
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class SectionChosenRepositoryImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var store: SectionChosenStore

    @Mock
    private lateinit var serviceNewsApi: ServiceNewsApi

    @Mock
    private lateinit var contentChosen: ContentChosen

    private val mapper: ContentDataMapperForDB = ContentDataMapperForDB()

    private lateinit var repository: SectionChosenRepositoryImpl

    private val testObserver: TestObserver<List<Content>> = TestObserver.create()

    @Before
    fun setUp() {
        repository = SectionChosenRepositoryImpl(store, serviceNewsApi, mapper, contentChosen)
    }

    @Test
    fun getSectionContent_Ok_ReturnedList() {
        val expectedPage = 11
        val expectedQuerry = "qq"
        val expectedUrl = "qwe"
        val expectedList = listOf(
            Content("aa", "bb", "cc", 12, "as", "aa")
        )
        val expectedContentResponse = SingleContentResponse(
            "id", "t", "s", "sN",
            "2018-01-31T06:55:20Z", "wT", "wU", "aU", "pN"
        )
        val expectedResponse = ContentResponse(
            null, null, null, null,
            null, null, null, listOf(expectedContentResponse)
        )
        given(contentChosen.query).willReturn(expectedQuerry)
        given(contentChosen.url).willReturn(expectedUrl)
        given(serviceNewsApi.getSectionChosen(anyString(), anyInt(), anyString()))
            .willReturn(Single.just(expectedResponse))

        repository.getSectionContent(expectedPage)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertNoErrors()
        val resultList = testObserver.values()[0]
        assertThat(resultList, hasSize(1))
        assertThat(resultList[0].name, equalTo(expectedContentResponse.webTitle))
        assertThat(resultList[0].type, equalTo(expectedContentResponse.type))
        verify(serviceNewsApi, times(1))
            .getSectionChosen(expectedUrl, expectedPage, expectedQuerry)
        verifyNoMoreInteractions(serviceNewsApi)
    }

    @Test
    fun getSectionContent_Error() {
        val throwable = Throwable("qwe")
        val expectedPage = 11
        val expectedQuerry = "qq"
        val expectedUrl = "qwe"
        val expectedList = listOf(
            Content("aa", "bb", "cc", 12, "as", "aa")
        )
        val expectedContentResponse = SingleContentResponse(
            "id", "t", "s", "sN",
            "w", "wT", "wU", "aU", "pN"
        )
        val expectedResponse = ContentResponse(
            null, null, null, null,
            null, null, null, listOf(expectedContentResponse)
        )
        given(contentChosen.query).willReturn(expectedQuerry)
        given(contentChosen.url).willReturn(expectedUrl)
        given(serviceNewsApi.getSectionChosen(anyString(), anyInt(), anyString()))
            .willReturn(Single.error(throwable))

        repository.getSectionContent(expectedPage)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()

        verify(serviceNewsApi, times(1))
            .getSectionChosen(expectedUrl, expectedPage, expectedQuerry)
        verifyNoMoreInteractions(serviceNewsApi)
    }
}