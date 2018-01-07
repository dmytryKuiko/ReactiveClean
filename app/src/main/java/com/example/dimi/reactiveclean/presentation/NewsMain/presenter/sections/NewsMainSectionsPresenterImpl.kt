package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.sections

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.sections.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.models.sections.SectionDisplayable
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class NewsMainSectionsPresenterImpl
@Inject constructor(interractor: NewsMainSectionsInterractor,
                    mapper: NewsMainSectionsDomainMapper) : NewsMainSectionsPresenter {
    private val compositeDisposable = CompositeDisposable()

    private val sectionDisplayableLiveData: MutableLiveData<List<SectionDisplayable>> = MutableLiveData()

    private val errorLiveData = SingleEventLiveData<String>()

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

    override fun getSingleEventLiveData(): LiveData<String> = errorLiveData

    override fun openCurrentSection(section: SectionDisplayable.Section) {
        Timber.d("SECTION %s", section.title)
    }

    private fun eventReceived(list: List<SectionDisplayable>) {
        sectionDisplayableLiveData.postValue(list)
    }

    private fun errorReceived(throwable: Throwable) {
        errorLiveData.postValue(throwable.localizedMessage)
    }
}
