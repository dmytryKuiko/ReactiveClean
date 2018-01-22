package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.db.ContentDao
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

class ContentStoreImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var contentDao: ContentDao

    @InjectMocks
    private lateinit var store: ContentStoreImpl

    @Mock
    private lateinit var contentMock: Content

    @Test
    fun storeAll() {
        val expectedList = listOf(contentMock)
        store.storeAll(expectedList)

        verify(contentDao, times(1)).insert(expectedList.toTypedArray())
        verifyNoMoreInteractions(contentDao)
    }

    @Test
    fun getAll_ResultExpected() {
        val content = Content("someName", "Article", "section",
            1516615203578, "http", "pillar")
        val expected = listOf(content)
        val testSubscriber = TestSubscriber.create<List<Content>>()
        given(contentDao.getAllContent()).willReturn(Flowable.just(expected))

        store.getAll()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expected)
            .assertNoErrors()

        verify(contentDao, times(1)).getAllContent()
        verifyNoMoreInteractions(contentDao)
    }

    @Test
    fun getAll_Error() {
        val throwable = Throwable()
        val error = Flowable.error<List<Content>>(throwable)
        val testSubscriber = TestSubscriber.create<List<Content>>()
        given(contentDao.getAllContent()).willReturn(error)

        store.getAll()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)

        verify(contentDao, times(1)).getAllContent()
        verifyNoMoreInteractions(contentDao)
    }

    @Test
    fun deleteAllAndStoreAll() {
        val expectedList = listOf(contentMock)
        store.deleteAllAndStoreAll(expectedList)

        verify(contentDao, times(1)).deleteAllArticlesAndInsertAll(expectedList)
        verifyNoMoreInteractions(contentDao)
    }
}