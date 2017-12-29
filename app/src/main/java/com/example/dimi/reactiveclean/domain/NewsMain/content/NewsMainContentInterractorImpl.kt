package com.example.dimi.reactiveclean.domain.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentRecyclerData
import com.example.dimi.reactiveclean.repositories.NewsMain.content.NewsMainContentRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsMainContentInterractorImpl
@Inject constructor(private val repository: NewsMainContentRepository) : NewsMainContentInterractor {

    override fun getContentStream(): Flowable<List<Content>> = repository.getAllContent()

    override fun loadNews(): Single<ContentPages> = repository.deleteAndFetchContent()

//    override fun getSpecificContentStream(params: String): Single<List<Content>> =
//            repository.getSpecificContentStream(params)

    override fun loadMoreContent(lastVisibleAndAllItems: Observable<ContentRecyclerData>,
                                 relay: PublishRelay<ContentPages>): Observable<ContentPages> {
        return Observable.zip(lastVisibleAndAllItems.skip(1).debounce(300, TimeUnit.MILLISECONDS).map {
                                    it.itemsCount - it.lastVisible < 5
                    }. distinctUntilChanged().filter { it },
                relay,
                BiFunction<Boolean, ContentPages, Int> { t1, pages ->
            pages.currentPage + 1
        }).flatMapSingle { repository.loadMoreContent(it) }
    }


//            lastVisibleAndAllItems
//                    .skip(1)
//                    .debounce(300, TimeUnit.MILLISECONDS)
//                    .map {
//                        it.second - it.first < 5
//                    }
//                    .distinctUntilChanged()
//                    .filter { it }
//                    .flatMapSingle { repository.loadMoreContent(2) }
}