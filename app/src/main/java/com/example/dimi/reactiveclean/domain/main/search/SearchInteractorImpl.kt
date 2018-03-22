package com.example.dimi.reactiveclean.domain.main.search

import android.view.inputmethod.EditorInfo
import com.example.dimi.reactiveclean.data.main.search.SearchRepository
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchModel
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.sql.Timestamp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchInteractorImpl
@Inject constructor(
    private val repository: SearchRepository,
    private val schedulers: SchedulersProvider
) : SearchInteractor {

    override fun listenSymbolTyped(listener: Observable<String>): Observable<List<SearchModel>> =
        Observable.combineLatest(
            listener.compose(this::composeListener),
            repository.getSearches(25).compose(this::composeGetSearches).toObservable(),
            BiFunction<String, List<SearchModel>, List<SearchModel>> { text, list ->
                list.filter { it.text.contains(text) }
            }
        ).distinctUntilChanged()

    override fun listenActionDone(listener: Observable<EditTextBindingModel>): Observable<String> =
        listener.filter { it.actionId == ACTION_DONE }
            .switchMap {
                Completable.fromCallable { storeSearch(it.text) }
                    .compose(this::setSchedulers)
                    .andThen(Observable.just(it.text))
            }

    private fun setSchedulers(completable: Completable): Completable =
        completable.subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())

    private fun storeSearch(text: String) {
        repository.storeSearch(
            SearchModel(text = text, dateTime = Timestamp(System.currentTimeMillis()))
        )
    }

    private fun composeGetSearches(flowable: Flowable<List<SearchModel>>): Flowable<List<SearchModel>> =
        flowable.map { it.distinctBy { it.text } }

    private fun composeListener(observable: Observable<String>): Observable<String> =
        observable.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()

    companion object {
        const val ACTION_DONE = EditorInfo.IME_ACTION_DONE
    }
}