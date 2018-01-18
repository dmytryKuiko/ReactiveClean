package com.example.dimi.reactiveclean.domain.main.search

import android.view.inputmethod.EditorInfo
import com.example.dimi.reactiveclean.data.main.search.SearchRepository
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.sql.Timestamp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchInterractorImpl
@Inject constructor(
    private val repository: SearchRepository
) : SearchInterractor {

    private val searchesList: MutableList<SearchModel> = mutableListOf()

    override fun storeSearch(text: String) {
        repository.storeSearch(
            SearchModel(text = text, dateTime = Timestamp(System.currentTimeMillis()))
        )
    }

    override fun getSearches(): Flowable<List<SearchModel>> =
        repository.getSearches(25)
            .map { it.distinctBy { it.text } }
            .doOnNext { searchesList.apply { clear() }.addAll(it) }


    override fun getResultsForSearch(text: String): Single<List<Content>> {
        return repository.getResultsForSearch(
            SearchModel(text = text, dateTime = Timestamp(System.currentTimeMillis()))
        )
    }

    override fun searchTyped(listener: Observable<String>): Observable<List<SearchModel>> =
        listener.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { text ->
                if (text.isBlank()) {
                    Observable.just(searchesList)
                } else {
                    Observable.fromCallable {
                        searchesList.filter { it.text.contains(text) }
                    }
                }
            }
            .distinctUntilChanged()

    override fun actionKeyboardTyped(listener: Observable<EditTextBindingModel>): Observable<EditTextBindingModel> =
        listener.filter { it.actionId == EditorInfo.IME_ACTION_DONE }
}