package com.example.dimi.reactiveclean.domain.main.sectionChosen

import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepository
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Rule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoMoreInteractions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class SectionChosenInteractorImplTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: SectionChosenRepository

    @InjectMocks
    private lateinit var interactor: SectionChosenInteractorImpl

    @Test
    fun getSectionContent_Ok_ReturnsTheSameList() {
        val expectedContent = Content(
            "name1", "t", "sN",
            12, "wU", "pN"
        )
        val expectedList = listOf(expectedContent)
        val expectedPage = 22
        val testObserver = TestObserver.create<List<Content>>()
        given(repository.getSectionContent(anyInt())).willReturn(Single.just(expectedList))

        interactor.getSectionContent(expectedPage)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(expectedList)
            .assertNoErrors()
        verify(repository, times(1)).getSectionContent(expectedPage)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getSectionContent_Error() {
        val throwable = Throwable("tt")
        val expectedPage = 22
        val testObserver = TestObserver.create<List<Content>>()
        given(repository.getSectionContent(anyInt())).willReturn(Single.error(throwable))

        interactor.getSectionContent(expectedPage)
            .subscribe(testObserver)
        testObserver.awaitTerminalEvent()

        testObserver.assertError(throwable)
        verify(repository, times(1)).getSectionContent(expectedPage)
        verifyNoMoreInteractions(repository)
    }
}