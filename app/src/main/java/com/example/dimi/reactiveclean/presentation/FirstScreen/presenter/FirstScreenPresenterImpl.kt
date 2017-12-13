package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.dimi.reactiveclean.di.scopes.FirstScreen
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractor
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenDomainMapper
import com.example.dimi.reactiveclean.models.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FirstScreen
class FirstScreenPresenterImpl
@Inject
constructor(private val firstScreenInterractor: FirstScreenInterractor<Nothing, List<Article>>,
            private val firstScreenDomainMapperArticleDisplayable: FirstScreenDomainMapper) :
        FirstScreenPresenter {
    private val compositeDisposable = CompositeDisposable()

    private val listArticles = MutableLiveData<List<ArticleDisplayableItem>>()

    private val showProgress = MutableLiveData<Int>()

    private val showError = SingleEventLiveData<Unit>()

    private val showUpdated = SingleEventLiveData<Unit>()

    init {
        val subscription = firstScreenInterractor.getArticlesStream(null)
                .doOnSubscribe({ showProgress.postValue(View.VISIBLE) })
                .map(firstScreenDomainMapperArticleDisplayable)
                .subscribe(this@FirstScreenPresenterImpl::eventReceived, this@FirstScreenPresenterImpl::errorDuringTheUpdate)
        compositeDisposable.add(subscription)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.dispose()
    }

    override fun getData(): LiveData<List<ArticleDisplayableItem>> = listArticles

    override fun getProgress(): LiveData<Int> = showProgress

    override fun getError(): LiveData<Unit> = showError

    override fun getSuccess(): LiveData<Unit> = showUpdated

    override fun onRefreshClicked() {
        val subscription = firstScreenInterractor.refreshArticles()
                .doOnSubscribe({ showProgress.postValue(View.VISIBLE) })
                .doFinally { showProgress.postValue(View.INVISIBLE) }
                .subscribe(this@FirstScreenPresenterImpl::successfullyUpdated,
                        this@FirstScreenPresenterImpl::errorDuringTheUpdate)
        compositeDisposable.add(subscription)
    }

    private fun eventReceived(list: List<ArticleDisplayableItem>) {
        showProgress.postValue(View.INVISIBLE)
        listArticles.postValue(list)
    }

    private fun errorDuringTheUpdate(error: Throwable) {
        showError.call()
    }

    private fun successfullyUpdated() {
        showUpdated.call()
    }
}