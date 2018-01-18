package com.example.dimi.reactiveclean.presentation.main.presenter.section

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.domain.main.section.NewsMainSectionsDomainMapper
import com.example.dimi.reactiveclean.domain.main.section.NewsMainSectionsInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SectionPresenterImpl
@Inject constructor(
    interractor: NewsMainSectionsInterractor,
    mapper: NewsMainSectionsDomainMapper,
    private val navigator: NewsMainNavigator,
    private val menuController: MenuController
) : SectionPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val sectionDisplayableLiveData: MutableLiveData<List<SectionDisplayable>> =
        MutableLiveData()

    private val errorLiveData = SingleEventLiveData<String>()

    init {
        interractor.getSectionsStream()
            .map(mapper)
            .subscribe(this::eventReceived, this::errorReceived)
            .addTo(compositeDisposable)
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
    }

    override fun getData(): LiveData<List<SectionDisplayable>> =
        sectionDisplayableLiveData

    override fun getSingleEventLiveData(): LiveData<String> = errorLiveData

    override fun openCurrentSection(section: SectionDisplayable.Section) {
        navigator.openChosenSection(sectionUrl = section.id, sectionTitle = section.title)
    }

    override fun openMenu() {
        menuController.open()
    }

    private fun eventReceived(list: List<SectionDisplayable>) {
        sectionDisplayableLiveData.postValue(list)
    }

    private fun errorReceived(throwable: Throwable) {
        errorLiveData.postValue(throwable.localizedMessage)
    }
}
