package com.example.dimi.reactiveclean.presentation.main.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.domain.main.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.main.content.ContentInteractor
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDB
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Named

class ContentPresenterImpl
@Inject constructor(
    private val interactor: ContentInteractor,
    private val mapper: ContentDomainMapper,
    @Named(DiConstants.NEWS_MAIN_CONTENT_PAGINATOR) private val paginator: PaginatorDB<ContentDisplayable>,
    private val navigator: NewsMainNavigator,
    private val menuController: MenuController
) : ContentPresenter {

    override fun getData(): LiveData<PaginatorModelResult<ContentDisplayable>> =
        paginator.getData()

    override fun getSingleEventData(): LiveData<String> {
        return paginator.getSingleEvent()
    }

    init {
        paginator.setCallbacks(
            init = this::initRequest, database = this::getDatabaseStream,
            nextData = this::getContentPage
        )
        refreshClicked()
    }

    override fun disposeSubscriptions() {
        paginator.disposeSubscriptions()
    }

    override fun refreshClicked() {
        paginator.refresh()
    }

    override fun loadNextContentPage() {
        paginator.loadNewPage()
    }

    override fun openCurrentContent(content: ContentDisplayable.Content) {
        navigator.openContentUrl(content.url)
    }

    override fun openMenuClicked() {
        menuController.open()
    }

    override fun searchClicked() {
        navigator.navigateToSearch()
    }

    private fun getContentPage(page: Int): Completable =
        interactor.loadNextContentPage(page)

    private fun initRequest(): Completable = interactor.loadNews()

    private fun getDatabaseStream(): Flowable<List<ContentDisplayable>> =
        interactor.getContentStream().map(mapper)
}
