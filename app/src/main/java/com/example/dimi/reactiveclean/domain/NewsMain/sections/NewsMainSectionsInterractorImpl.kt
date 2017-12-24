package com.example.dimi.reactiveclean.domain.NewsMain.sections

import com.example.dimi.reactiveclean.models.sections.Section
import com.example.dimi.reactiveclean.repositories.NewsMain.sections.NewsMainSectionsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

class NewsMainSectionsInterractorImpl
@Inject constructor(private val repository: NewsMainSectionsRepository) : NewsMainSectionsInterractor {
    override fun getSectionsStream(): Flowable<List<Section>> = repository.getAllSections()
            .flatMapSingle(this::fetchWhenNoneThenDrafts)
            .filter { list -> list.isNotEmpty() }

    override fun getSpecificSectionsStream(params: String): Flowable<List<Section>> {
        return repository.getSpecificSections(params)
    }

    private fun fetchWhenNoneThenDrafts(list: List<Section>): Single<List<Section>> =
            fetchWhenNone(list).andThen(just(list))

    private fun fetchWhenNone(list: List<Section>): Completable =
            when (list.isEmpty()) {
                true -> repository.fetchSections()
                else -> Completable.complete()
            }
}