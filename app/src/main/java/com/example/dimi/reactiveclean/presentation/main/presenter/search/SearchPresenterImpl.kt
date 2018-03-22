package com.example.dimi.reactiveclean.presentation.main.presenter.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.main.search.SearchDomainMapperDB
import com.example.dimi.reactiveclean.domain.main.search.SearchInteractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.search.EditTextBindingModel
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class SearchPresenterImpl
@Inject constructor(
    private val interactor: SearchInteractor,
    private val navigator: NewsMainNavigator,
    private val mapper: SearchDomainMapperDB
) : SearchPresenter {

    private val searchesLiveData: MutableLiveData<List<SearchDisplayable.Search>> =
        MutableLiveData()

    private val listenerCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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
        interactor.listenSymbolTyped(listener)
            .map(mapper)
            .subscribe (searchesLiveData::postValue, this::handleError)
            .addTo(listenerCompositeDisposable)
    }

    override fun listenEditTextModel(listener: Observable<EditTextBindingModel>) {
        interactor.listenActionDone(listener)
            .subscribe(navigator::openSearchContent, this::handleError)
            .addTo(listenerCompositeDisposable)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }

    private fun handleError(throwable: Throwable) {
        Timber.d(throwable)
    }
}