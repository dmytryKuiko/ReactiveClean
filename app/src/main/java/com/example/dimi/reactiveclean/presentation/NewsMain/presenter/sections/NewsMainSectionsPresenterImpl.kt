package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.models.sections.SectionDisplayable
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.jakewharton.rxbinding2.InitialValueObservable
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsMainSectionsPresenterImpl
@Inject constructor(private val interractor: NewsMainSectionsInterractor,
                    private val mapper: NewsMainSectionsDomainMapper) : NewsMainSectionsPresenter {
    private val compositeDisposable = CompositeDisposable()

    private val sectionDisplayableLiveData: MutableLiveData<List<SectionDisplayable>> = MutableLiveData()

    private val showErrorLiveData = SingleEventLiveData<Unit>()

    init {
        val disposable = interractor.getSectionsStream()
                .map(mapper)
                .subscribe(this::eventReceived, this::errorReceived)

        compositeDisposable.add(disposable)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun getData(): LiveData<List<SectionDisplayable>> = sectionDisplayableLiveData

    override fun getError(): LiveData<Unit> = showErrorLiveData

    private fun eventReceived(list: List<SectionDisplayable>) {
        sectionDisplayableLiveData.postValue(list)
    }

    private fun errorReceived(throwable: Throwable) {
        showErrorLiveData.call()
    }
}
