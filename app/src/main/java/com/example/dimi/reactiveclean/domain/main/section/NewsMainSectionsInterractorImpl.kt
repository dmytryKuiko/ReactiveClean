package com.example.dimi.reactiveclean.domain.main.section

import com.example.dimi.reactiveclean.models.section.Section
import com.example.dimi.reactiveclean.data.main.section.SectionRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

class NewsMainSectionsInterractorImpl
@Inject constructor(private val repository: SectionRepository) : NewsMainSectionsInterractor {

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