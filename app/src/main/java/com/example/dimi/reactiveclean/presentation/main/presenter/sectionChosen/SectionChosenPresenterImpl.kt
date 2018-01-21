package com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.domain.main.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInteractor
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorNetwork
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.models.section.ContentChosen
import com.example.dimi.reactiveclean.models.section.ToolbarData
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SectionChosenPresenterImpl
@Inject constructor(
    private val interactor: SectionChosenInteractor,
    private val mapper: ContentDomainMapper,
    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR) private val paginator: PaginatorNetwork<ContentDisplayable>,
    private val navigator: NewsMainNavigator,
    private val menuController: MenuController,
    private val contentChosen: ContentChosen
) : SectionChosenPresenter {

    private val toolbarData: MutableLiveData<ToolbarData> = MutableLiveData()

    init {
        toolbarData.value = contentChosen.toolbar
        paginator.setCallback(this::getSectionContent)
        paginator.refresh()
    }

    override fun disposeSubscriptions() {
        paginator.disposeSubscriptions()
    }

    override fun getData(): LiveData<PaginatorModelResult<ContentDisplayable>> =
        paginator.getData()

    override fun getNetworkError(): LiveData<String> = paginator.getSingleEvent()

    override fun refreshContent() {
        paginator.refresh()
    }

    override fun loadNextContentPage() {
        paginator.loadNextPage()
    }

    override fun openCurrentContent(content: ContentDisplayable.Content) {
        navigator.openContentUrl(content.url)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }

    override fun openMenuClicked() {
        when (contentChosen.toolbar) {
            is ToolbarData.SectionToolbar -> menuController.open()
            is ToolbarData.SearchToolbar -> navigator.backToMainScreen()
        }
    }

    override fun searchClicked() {
        when (contentChosen.toolbar) {
            is ToolbarData.SectionToolbar -> navigator.navigateToSearch()
            is ToolbarData.SearchToolbar -> navigator.onBackPressed()
        }
    }

    override fun getToolbarData(): LiveData<ToolbarData> = toolbarData


    private fun getSectionContent(page: Int): Single<List<ContentDisplayable>> =
        interactor.getSectionContent(page).map(mapper)
}