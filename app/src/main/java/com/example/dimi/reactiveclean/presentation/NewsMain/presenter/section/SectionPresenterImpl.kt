package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.NewsMain.section.NewsMainSectionsDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.section.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.navigation.NewsMain.NewsMainNavigator
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class SectionPresenterImpl
@Inject constructor(interractor: NewsMainSectionsInterractor,
                    mapper: NewsMainSectionsDomainMapper,
                    private val navigator: NewsMainNavigator) : SectionPresenter {
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
        
    }

    private fun eventReceived(list: List<SectionDisplayable>) {
        sectionDisplayableLiveData.postValue(list)
    }

    private fun errorReceived(throwable: Throwable) {
        errorLiveData.postValue(throwable.localizedMessage)
    }
}
