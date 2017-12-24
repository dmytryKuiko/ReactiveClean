package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsMainContentPresenterImpl
@Inject constructor(private val interractor: NewsMainContentInterractor,
                    private val mapper: NewsMainContentDomainMapper) : NewsMainContentPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val sectionDisplayableLiveData: MutableLiveData<List<ContentDisplayable>> = MutableLiveData()

    private val showErrorLiveData = SingleEventLiveData<Unit>()

    private lateinit var disposableSearch: Disposable

    init {
        val disposable = interractor.getContentStream()
                .map(mapper)
                .subscribe(this::eventReceived, this::errorReceived)

        compositeDisposable.add(disposable)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun getData(): LiveData<List<ContentDisplayable>> = sectionDisplayableLiveData

    override fun getError(): LiveData<Unit> = showErrorLiveData

    private fun eventReceived(list: List<ContentDisplayable>) = sectionDisplayableLiveData.postValue(list)

    private fun errorReceived(throwable: Throwable) = showErrorLiveData.call()
}