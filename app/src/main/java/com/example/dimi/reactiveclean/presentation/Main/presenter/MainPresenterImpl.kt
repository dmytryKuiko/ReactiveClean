package com.example.dimi.reactiveclean.presentation.Main.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.dimi.reactiveclean.di.scopes.MainScope
import com.example.dimi.reactiveclean.domain.Main.MainInterractor
import com.example.dimi.reactiveclean.domain.Main.MainDomainMapper
import com.example.dimi.reactiveclean.models.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@MainScope
class MainPresenterImpl
@Inject
constructor(private val mainInterractor: MainInterractor<Nothing, List<Article>>,
            private val mainDomainMapperArticleDisplayable: MainDomainMapper) :
        MainPresenter {
    private val compositeDisposable = CompositeDisposable()

    private val listArticles = MutableLiveData<List<ArticleDisplayableItem>>()

    private val showProgress = MutableLiveData<Int>()

    private val showError = SingleEventLiveData<Unit>()

    private val showUpdated = SingleEventLiveData<Unit>()

    init {
        val subscription = mainInterractor.getArticlesStream(null)
                .doOnSubscribe({ showProgress.postValue(View.VISIBLE) })
                .map(mainDomainMapperArticleDisplayable)
                .subscribe(this@MainPresenterImpl::eventReceived, this@MainPresenterImpl::errorDuringTheUpdate)
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
        val subscription = mainInterractor.refreshArticles()
                .doOnSubscribe({ showProgress.postValue(View.VISIBLE) })
                .doFinally { showProgress.postValue(View.INVISIBLE) }
                .subscribe(this@MainPresenterImpl::successfullyUpdated,
                        this@MainPresenterImpl::errorDuringTheUpdate)
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