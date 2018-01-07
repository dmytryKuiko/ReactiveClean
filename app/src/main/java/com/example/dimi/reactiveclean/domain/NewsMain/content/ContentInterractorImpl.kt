package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.repositories.NewsMain.content.ContentRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ContentInterractorImpl
@Inject constructor(private val repository: ContentRepository) : ContentInterractor {

    override fun getContentStream(): Flowable<List<Content>> = repository.getAllContent()

    override fun loadNews(): Completable = repository.deleteAndFetchContent()

    override fun searchContent(text: Observable<String>): Observable<ContentPages> {
        return text.debounce(300, TimeUnit.MILLISECONDS)
                .filter { it.length > 2 }
                .distinctUntilChanged()
                .switchMap { repository.searchContent(it).toObservable() }
    }

    override fun loadNextContentPage(page: Int): Completable {
        return repository.loadNextContentPage(page)
    }
}