package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.repositories.NewsMain.content.NewsMainContentRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentInterractorImpl
@Inject constructor(private val repository: NewsMainContentRepository) : NewsMainContentInterractor {
    override fun getContentStream(): Flowable<List<Content>> = repository.getAllContent()
            .flatMapSingle(this::fetchWhenNoneThenDrafts)
            .filter { list -> list.isNotEmpty() }

    override fun getSpecificContentStream(params: String): Single<List<Content>> {
        return repository.getSpecificContentStream(params)
    }

    private fun fetchWhenNoneThenDrafts(list: List<Content>): Single<List<Content>> =
            fetchWhenNone(list).andThen(Single.just(list))

    private fun fetchWhenNone(list: List<Content>): Completable =
            when (list.isEmpty()) {
                true -> repository.fetchContent()
                false -> Completable.complete()
            }
}