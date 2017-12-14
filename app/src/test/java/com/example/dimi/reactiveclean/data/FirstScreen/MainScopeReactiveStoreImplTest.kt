package com.example.dimi.reactiveclean.data.FirstScreen

import com.example.dimi.reactiveclean.data.Main.MainReactiveStoreImpl
import com.example.dimi.reactiveclean.data.db.NewsDao
import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Rule
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MainScopeReactiveStoreImplTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var store: NewsDao

    @Mock
    lateinit var article: Article

    @InjectMocks
    lateinit var mainReactiveStore: MainReactiveStoreImpl

    inline private fun <reified T : Any> mock(): T = mock(T::class.java)

    @Test
    fun storeSingular_SameObject() {
        mainReactiveStore.storeSingular(article)

        verify(store, times(1)).insert(article)
        Mockito.verifyNoMoreInteractions(store)
    }

    @Test
    fun storeAll() {
        val articleList = listOf(article)
        mainReactiveStore.storeAll(articleList)

        verify(store, times(1)).insert(articleList.toTypedArray())
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getSingular() {
        given(store.getSingleResponseData(1)).willReturn(Flowable.just(article))

        val article2: Article = mock()

        val testSubscriber = TestSubscriber.create<Article>()

        mainReactiveStore.getSingular(1).subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(article)
        testSubscriber.assertValue(article)
    }

    @Test
    fun getAll() {
        val articleList = listOf(article)
        given(store.getAllResponseData()).willReturn(Flowable.just(articleList))

        val testSubscriber = TestSubscriber.create<List<Article>>()

        mainReactiveStore.getAll().subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(articleList)
    }

}