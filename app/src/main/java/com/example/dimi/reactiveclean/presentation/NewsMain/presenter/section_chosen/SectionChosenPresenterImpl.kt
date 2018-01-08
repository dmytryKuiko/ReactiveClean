package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen

import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.domain.NewsMain.content.ContentDomainMapper
import com.example.dimi.reactiveclean.domain.NewsMain.section_chosen.SectionChosenInterractor
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.utils.paginator.PaginatorChanged
import javax.inject.Inject
import javax.inject.Named

class SectionChosenPresenterImpl
@Inject constructor(private val interractor: SectionChosenInterractor,
                    private val mapper: ContentDomainMapper,
                    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR) private val paginator: PaginatorChanged<ContentDisplayable>,
                    @Named(DiConstants.SECTION_CHOSEN_URL) private val data: String
) : SectionChosenPresenter {
}