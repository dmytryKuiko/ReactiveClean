package com.example.dimi.reactiveclean.domain.main.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.data.main.content.ContentRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ContentInteractorImpl
@Inject constructor(private val repository: ContentRepository) : ContentInteractor {

    override fun getContentStream(): Flowable<List<Content>> = repository.getAllContent()

    override fun loadNews(): Completable = repository.deleteAndFetchContent()

    override fun loadNextContentPage(page: Int): Completable {
        return repository.loadNextContentPage(page)
    }
}