package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.data.db.SectionsDao
import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit

class SectionStoreImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var sectionDao: SectionsDao

    @InjectMocks
    private lateinit var store: SectionStoreImpl

    private val testSubscriber: TestSubscriber<List<Section>> = TestSubscriber.create()

    @Test
    fun storeAll_Ok_ListSaved() {
        val expectedList = listOf(Section("123", "qwe", "qwe", "aa"))

        store.storeAll(expectedList)

        verify(sectionDao, times(1)).insert(expectedList.toTypedArray())
        verifyNoMoreInteractions(sectionDao)
    }

    @Test
    fun getAllSections_Ok_ReturnedList() {
        val expectedList = listOf(
            Section("a", "b", "c", "d"),
            Section("aa", "bb", "cc", "dd")
        )
        given(sectionDao.getAllSections()).willReturn(Flowable.just(expectedList))

        store.getAllSections()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()
        verify(sectionDao, times(1)).getAllSections()
        verifyNoMoreInteractions(sectionDao)
    }

    @Test
    fun getAllSections_Error() {
        val throwable = Throwable("asd")
        given(sectionDao.getAllSections()).willReturn(Flowable.error(throwable))

        store.getAllSections()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(sectionDao, times(1)).getAllSections()
        verifyNoMoreInteractions(sectionDao)
    }

    @Test
    fun getSpecificSections_Ok_ReturnedList() {
        val expectedParams = "aaaaa"
        val expectedList = listOf(
            Section("a", "b", "c", "d"),
            Section("aa", "bb", "cc", "dd")
        )
        given(sectionDao.getSpecificSections(anyString())).willReturn(Flowable.just(expectedList))

        store.getSpecificSections(expectedParams)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedList)
            .assertNoErrors()
        verify(sectionDao, times(1)).getSpecificSections(expectedParams)
        verifyNoMoreInteractions(sectionDao)
    }

    @Test
    fun getSpecificSections_Error() {
        val expectedParams = "qwe"
        val throwable = Throwable("qqq")
        given(sectionDao.getSpecificSections(anyString())).willReturn(Flowable.error(throwable))

        store.getSpecificSections(expectedParams)
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(sectionDao, times(1)).getSpecificSections(expectedParams)
        verifyNoMoreInteractions(sectionDao)
    }
}