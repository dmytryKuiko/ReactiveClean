package com.example.dimi.reactiveclean.domain.main.content

import com.example.dimi.reactiveclean.data.main.content.ContentRepository
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

class ContentInteractorImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: ContentRepository

    @InjectMocks
    private lateinit var interactor: ContentInteractorImpl

    private val page: Int = 7

    private val testSubscriber: TestSubscriber<List<Content>> = TestSubscriber.create()

    private val testObserver: TestObserver<Completable> = TestObserver.create()

    private val throwable: Throwable = Throwable("SomeThrowable")

    @Test
    fun getContentStream_ResultExpected() {
        val content = Content("someName", "Article", "section",
            1516615203578, "http", "pillar")
        val expected = listOf(content)
        BDDMockito.given(repository.getAllContent()).willReturn(Flowable.just(expected))

        interactor.getContentStream()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertValue(expected)
            .assertNoErrors()
        verify(repository, times(1)).getAllContent()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getContentStream_Error() {
        val error = Flowable.error<List<Content>>(throwable)
        BDDMockito.given(repository.getAllContent()).willReturn(error)

        interactor.getContentStream()
            .subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertError(throwable)
        verify(repository, times(1)).getAllContent()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun loadNews_NoError() {
        given(repository.deleteAndFetchContent()).willReturn(Completable.complete())

        interactor.loadNews()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
        verify(repository, times(1)).deleteAndFetchContent()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun loadNews_Error() {
        val error = Completable.error(throwable)
        given(repository.deleteAndFetchContent()).willReturn(error)

        interactor.loadNews()
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verify(repository, times(1)).deleteAndFetchContent()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun loadNextContentPage_NoError() {
        given(repository.loadNextContentPage(page)).willReturn(Completable.complete())

        interactor.loadNextContentPage(page)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
        verify(repository, times(1)).loadNextContentPage(page)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun loadNextContentPage_Error() {
        val error = Completable.error(throwable)
        val testObserver = TestObserver.create<Completable>()
        given(repository.loadNextContentPage(page)).willReturn(error)

        interactor.loadNextContentPage(page)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
            .assertTerminated()
        verify(repository, times(1)).loadNextContentPage(page)
        verifyNoMoreInteractions(repository)
    }
}