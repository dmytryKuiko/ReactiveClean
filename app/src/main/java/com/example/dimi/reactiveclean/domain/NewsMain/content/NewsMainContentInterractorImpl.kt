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
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsMainContentInterractorImpl
@Inject constructor(private val repository: NewsMainContentRepository) : NewsMainContentInterractor {

    override fun getContentStream(): Flowable<List<BaseItem>> = repository.getAllContent()

    override fun loadNews(): Completable = repository.deleteAndFetchContent()

    override fun getSpecificContentStream(params: String): Single<List<Content>> =
            repository.getSpecificContentStream(params)

    override fun loadMoreContent(lastVisibleAndAllItems: Observable<Pair<Int, Int>>): Completable =
            lastVisibleAndAllItems.skip(1)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .map {
                        it.second - it.first < 5
                    }
                    .distinctUntilChanged()
                    .filter { it }
                    .flatMapCompletable { repository.loadMoreContent() }
}