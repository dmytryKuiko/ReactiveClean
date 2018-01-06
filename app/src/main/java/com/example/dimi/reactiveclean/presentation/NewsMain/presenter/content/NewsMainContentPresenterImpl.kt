package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.support.customtabs.CustomTabsIntent
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.content.NewsMainContentInterractor
import com.example.dimi.reactiveclean.extensions.addTo
import com.example.dimi.reactiveclean.utils.paginator.PaginatorChanged
import com.example.dimi.reactiveclean.utils.paginator.PaginatorResult
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class NewsMainContentPresenterImpl
@Inject constructor(private val interractor: NewsMainContentInterractor,
                    private val mapper: NewsMainContentDomainMapper,
                    @Named(DiConstants.NEWS_MAIN_CONTENT_PAGINATOR) private val paginator: PaginatorChanged<ContentDisplayable>
) : NewsMainContentPresenter {

    private var lastPosition: Int = 0

    private var compositeDisposable = CompositeDisposable()

    override fun getData(): LiveData<PaginatorResult<ContentDisplayable>> {
        return paginator.getData()
    }

    init {
        paginator.setCallbacks(
                init = this::initRequest, database = this::getDatabaseStream,
                nextData = this::getContentPage
        )
        refreshContent()
    }

    override fun disposeSubscriptions() {
        compositeDisposable.clear()
        paginator.disposeSubscriptions()
    }

    override fun refreshContent() {
        paginator.refresh()
    }

    override fun loadNextContentPage() {
        paginator.loadNewPage()
    }

    override fun openCurrentContent(content: ContentDisplayable.Content) {
        Timber.d("POSITION CLICKED %s", content.url)

    }

    override fun setVisibleItem(position: Int) {
        lastPosition = position
    }

    override fun getVisibleItem(): Int = lastPosition

    override fun subscribeSearchText(text: Observable<String>) {
        interractor.searchContent(text)
                .subscribe({
                    var a = 3
                    a++
                }, {
                    var a = 3
                    a++
                })
                .addTo(compositeDisposable)

    }

    override fun disposeRxBinding() {
        compositeDisposable.clear()
    }

    private fun getContentPage(page: Int): Completable {
        return interractor.loadNextContentPage(page)
    }

    private fun initRequest(): Completable {
        return interractor.loadNews()
    }

    private fun getDatabaseStream(): Flowable<List<ContentDisplayable>> {
        return interractor.getContentStream().map(mapper)
    }
}
