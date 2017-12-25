package com.example.dimi.reactiveclean.domain.NewsMain.content

import android.support.v7.widget.LinearLayoutManager
import com.example.dimi.reactiveclean.base.BaseItem
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.repositories.NewsMain.content.NewsMainContentRepository
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import javax.inject.Inject

class NewsMainContentInterractorImpl
@Inject constructor(private val repository: NewsMainContentRepository) : NewsMainContentInterractor {

    private var currentListSize: Int = 0

    override fun getContentStream(): Flowable<List<BaseItem>> = repository.getAllContent()
            .doOnNext { list -> currentListSize = list.size }

    override fun loadNews(): Completable = repository.deleteAndFetchContent()

    override fun getSpecificContentStream(params: String): Single<List<Content>> =
            repository.getSpecificContentStream(params)

    override fun loadMoreContent(listener: Observable<Int>): Completable =
            listener
                    .map { position -> position != 0 && currentListSize - position < 5 }
                    .distinctUntilChanged()
                    .filter { mapTrue -> mapTrue }
                    .flatMapCompletable { repository.loadMoreContent() }
}