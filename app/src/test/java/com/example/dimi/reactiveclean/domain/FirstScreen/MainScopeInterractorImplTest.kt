package com.example.dimi.reactiveclean.domain.FirstScreen

import com.example.dimi.reactiveclean.domain.Main.MainInterractorImpl
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.repositories.Main.MainRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.BDDMockito.*
import org.mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/**
 * Created by dimi on 07.12.2017.
 */
class MainScopeInterractorImplTest {

    inline private fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    lateinit var repository: MainRepository

    lateinit var interractor: MainInterractorImpl

    @Before
    fun setUp() {
        repository = Mockito.mock(MainRepository::class.java)
        interractor = MainInterractorImpl(repository)
    }

    @Test
    fun getArticlesStream_success() {
//        val repo = Mockito.mock(MainRepository::class.java)
//        val firstScreenInterractor: MainInterractorImpl = Mockito.mock(MainInterractorImpl::class.java)

        val article = Mockito.mock(Article::class.java)

        val list = listOf(article)

        given(repository.getAllArticles()).willReturn(Flowable.fromCallable { list })

        val testSubscriber = TestSubscriber.create<List<Article>>()

        interractor.getArticlesStream(null).subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(list)

//        val model = testSubscriber.events.
//                assertThat(model, )
    }

    @Test
    fun getArticlesStream_RepositoryShould_Fetch() {
        given(repository.getAllArticles()).willReturn(Flowable.fromCallable { emptyList<Article>() })
        //given(repository.fetchArticles()).willReturn(Completable.complete())

        val testSubscriber = TestSubscriber.create<List<Article>>()

        interractor.getArticlesStream(null).subscribe(testSubscriber)
        testSubscriber.awaitTerminalEvent()

        Mockito.verify(repository).fetchArticles()


    }

}