package com.example.dimi.reactiveclean.presentation.main.presenter.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.main.search.SearchDomainMapperDB
import com.example.dimi.reactiveclean.domain.main.search.SearchInteractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchPresenterImpl
@Inject constructor(
    private val interactor: SearchInteractor,
    private val navigator: NewsMainNavigator,
    private val mapper: SearchDomainMapperDB,
    private val schedulers: SchedulersProvider
) : SearchPresenter {

    private val searchesLiveData: MutableLiveData<List<SearchDisplayable.Search>> =
        MutableLiveData()

    private val listenerCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var disposable: Disposable? = null

    override fun getData(): LiveData<List<SearchDisplayable.Search>> = searchesLiveData

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun disposeRxBinding() {
        listenerCompositeDisposable.clear()
    }

    override fun previousSearchClicked(search: SearchDisplayable.Search) {
        navigator.openSearchContent(search.text)
    }

    override fun listenEditText(listener: Observable<String>) {
        interactor.searchTyped(listener)
            .map(mapper)
            .subscribe {
                searchesLiveData.postValue(it)
            }
            .addTo(listenerCompositeDisposable)
    }

    override fun listenEditTextAction(listener: Observable<EditTextBindingModel>) {
        interactor.actionKeyboardTyped(listener)
            .subscribe {
                disposable?.dispose()
                disposable = Completable.fromCallable { interactor.storeSearch(it.text) }
                    .compose(this::composeSchedulers)
                    .subscribe {
                        navigator.openSearchContent(it.text)
                    }
            }
            .addTo(listenerCompositeDisposable)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }

    private fun composeSchedulers(completable: Completable): Completable =
        completable.subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())
}