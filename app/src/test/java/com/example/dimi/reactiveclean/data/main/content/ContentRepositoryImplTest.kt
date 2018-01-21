package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.Mockito.*

class ContentRepositoryImplTest {

    private val store: ContentStore = mock(ContentStore::class.java)

    private val serviceNewsApi: ServiceNewsApi = mock(ServiceNewsApi::class.java)

    private val mapperDB: ContentDataMapperForDB = ContentDataMapperForDB()

    private val testSubscriber: TestSubscriber<List<Content>> = TestSubscriber.create()

    private val testObserver: TestObserver<ContentResponse> = TestObserver.create()

    private val repository: ContentRepositoryImpl = ContentRepositoryImpl(store, serviceNewsApi, mapperDB)

    @Test
    fun getAllContent_ReturnedExisted() {
        val expectedContent = listOf(
            Content(
                "name1", "type2", "secName3",
                33, "http:", "pillarName"
            )
        )
        given(store.getAll()).willReturn(Flowable.just(expectedContent))

        repository.getAllContent()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expectedContent)
            .assertNoErrors()
        verify(store, times(1)).getAll()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun getAllContent_Error() {
        val throwable = Throwable()
        val error = Flowable.error<List<Content>>(throwable)
        given(store.getAll()).willReturn(error)

        repository.getAllContent()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
            .assertTerminated()
        verify(store, times(1)).getAll()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun deleteAndFetchContent_ReturnedExisted() {
        val contentResponse = mock(ContentResponse::class.java)
        given(serviceNewsApi.getAllContent()).willReturn(Single.just(contentResponse))

        repository.deleteAndFetchContent()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
        verify(store, times(1)).deleteAllAndStoreAll(listOf())
        verifyNoMoreInteractions(store)
    }

    @Test
    fun deleteAndFetchContent_Error() {
        val throwable = Throwable()
        val error = Single.error<ContentResponse>(throwable)
        given(serviceNewsApi.getAllContent()).willReturn(error)

        repository.deleteAndFetchContent()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verifyNoMoreInteractions(store)
    }

    @Test
    fun loadNextContentPage_ReturnedExisted() {
        val contentResponse = mock(ContentResponse::class.java)
        given(serviceNewsApi.getNextContent(1)).willReturn(Single.just(contentResponse))

        repository.loadNextContentPage(1)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
        verify(store, times(1)).storeAll(listOf())
        verifyNoMoreInteractions(store)
    }

    @Test
    fun loadNextContentPage_Error() {
        val throwable = Throwable()
        val error = Single.error<ContentResponse>(throwable)
        given(serviceNewsApi.getNextContent(1)).willReturn(error)

        repository.loadNextContentPage(1)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verifyNoMoreInteractions(store)
    }

}