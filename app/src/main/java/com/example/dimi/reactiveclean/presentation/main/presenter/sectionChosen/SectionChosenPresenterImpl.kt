package com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.domain.main.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInterractor
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.extensions.paginator.Paginator
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorModelResult
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SectionChosenPresenterImpl
@Inject constructor(
        private val interractor: SectionChosenInterractor,
        private val mapper: ContentDomainMapper,
        @Named(DiConstants.SECTION_CHOSEN_PAGINATOR) private val paginator: Paginator<ContentDisplayable>,
        private val navigator: NewsMainNavigator,
        @Named(DiConstants.SECTION_CHOSEN_URL) private val sectionChosen: String
) : SectionChosenPresenter {

    private var lastPosition: Int = 0

    init {
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

    override fun setVisibleItem(position: Int) {
        lastPosition = position
    }

    override fun getVisibleItem(): Int = lastPosition

    override fun getSectionChosen(): String = sectionChosen

    private fun getSectionContent(page: Int): Single<List<ContentDisplayable>> =
            interractor.getSectionContent(page).map(mapper)
}