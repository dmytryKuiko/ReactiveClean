package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenDomainMapper
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractor
import com.example.dimi.reactiveclean.models.*
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.FirstScreenView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FirstScreenPresenterImpl
@Inject
constructor(private val firstScreenInterractor: FirstScreenInterractor<Nothing, List<Article>>,
            private val firstScreenDomainMapperArticleDisplayable: FirstScreenDomainMapper,
            private var cache: FirstScreenPresenterCache) : FirstScreenPresenter {

    private val compositeDisposable = CompositeDisposable()

    private var view: FirstScreenView? = null

    init {
        compositeDisposable.add(bindToArticleDisplayed())
    }

    override fun bindView(view: FirstScreenView) {
        this.view = view
        updateView(view)
    }

    override fun unbindView() {
        view = null
    }

    override fun onRefreshClicked() {
        refreshStarted()
        val subscription = firstScreenInterractor.refreshArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(this@FirstScreenPresenterImpl::refreshFinished)
                .subscribe(this@FirstScreenPresenterImpl::showSuccessfullyUpdated,
                        this@FirstScreenPresenterImpl::showError)
        compositeDisposable.add(subscription)
    }

    override fun clearSubscriptions() {
        compositeDisposable.clear()
    }

    private fun bindToArticleDisplayed() =
            firstScreenInterractor.getArticlesStream(null)
                    .map(firstScreenDomainMapperArticleDisplayable)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this@FirstScreenPresenterImpl::updateCacheAndShowArticles,
                            this@FirstScreenPresenterImpl::showError)

    private fun updateView(view: FirstScreenView) {
        if(cache.showProgress) {
            view.showProgress()
        }
        view.getData(cache.articleList)
    }

    private fun showSuccessfullyUpdated() {
        view?.showDataSynchronized()
    }

    private fun updateCacheAndShowArticles(list: List<ArticleDisplayableItem>) {
        cache.showProgress = false
        cache.articleList.clear()
        cache.articleList.addAll(list)
        view?.getData(list)
        view?.hideProgress()
    }

    private fun showError(t: Throwable) {
        view?.showError()
        cache.showProgress = false
    }

    private fun refreshStarted() {
        view?.disableRefreshButton()
        cache.showProgress = true
        view?.showProgress()
    }

    private fun refreshFinished() {
        cache.showProgress = false
        view?.hideProgress()
        view?.enableRefreshButton()
    }
}
