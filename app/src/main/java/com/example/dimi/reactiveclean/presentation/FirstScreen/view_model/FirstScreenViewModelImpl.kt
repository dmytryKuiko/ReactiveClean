package com.example.dimi.reactiveclean.presentation.FirstScreen.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenInterractor
import com.example.dimi.reactiveclean.domain.FirstScreen.FirstScreenDomainMapper
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import com.example.dimi.reactiveclean.models.ErrorModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FirstScreenViewModelImpl
@Inject
constructor(val firstScreenInterractor: FirstScreenInterractor<Nothing, List<Article>>,
            val firstScreenDomainMapperArticleDisplayable: FirstScreenDomainMapper) : ViewModel(),
        FirstScreenViewModel<ErrorModel,List<ArticleDisplayableItem>> {

    private val compositeDisposable = CompositeDisposable()

    private val liveDataArticleDisplayable = MutableLiveData<List<ArticleDisplayableItem>>()

    private val liveDataError = MutableLiveData<ErrorModel>()

    init {
        compositeDisposable.add(bindToArticleDisplayed())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    override fun provideData(): LiveData<List<ArticleDisplayableItem>> = liveDataArticleDisplayable

    override fun provideError(): LiveData<ErrorModel> = liveDataError

    private fun bindToArticleDisplayed() =
            firstScreenInterractor.getArticlesStream(null)
                    .map(firstScreenDomainMapperArticleDisplayable).retry(4)
                    .subscribe(liveDataArticleDisplayable::postValue, { error-> liveDataError.postValue(ErrorModel(error.localizedMessage))})
}