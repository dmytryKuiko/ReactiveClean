package com.example.dimi.reactiveclean.presentation.main.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.domain.main.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.main.content.ContentInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorDB
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ContentPresenterImpl
@Inject constructor(
        private val interractor: ContentInterractor,
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

    override fun openDrawerClicked() {
        menuController.open()
    }

    override fun searchClicked() {
        navigator.navigateToSearch()
    }

    private fun getContentPage(page: Int): Completable =
            interractor.loadNextContentPage(page)

    private fun initRequest(): Completable = interractor.loadNews()

    private fun getDatabaseStream(): Flowable<List<ContentDisplayable>> =
            interractor.getContentStream().map(mapper)
}
