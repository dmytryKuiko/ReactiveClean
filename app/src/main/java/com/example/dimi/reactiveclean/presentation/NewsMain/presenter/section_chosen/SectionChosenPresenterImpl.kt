package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen

import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.domain.NewsMain.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.section_chosen.SectionChosenInterractor
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.utils.paginator.Paginator
import com.example.dimi.reactiveclean.utils.paginator.PaginatorDB
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SectionChosenPresenterImpl
@Inject constructor(private val interractor: SectionChosenInterractor,
                    private val mapper: ContentDomainMapper,
                    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR) private val paginator: Paginator<ContentDisplayable>
) : SectionChosenPresenter {

    init {
        paginator.setCallback(this::getSectionContent)
        paginator.refresh()
    }

    private fun getSectionContent(page: Int): Single<List<ContentDisplayable>> {
        return interractor.getSectionContent(page).map(mapper)
    }
}