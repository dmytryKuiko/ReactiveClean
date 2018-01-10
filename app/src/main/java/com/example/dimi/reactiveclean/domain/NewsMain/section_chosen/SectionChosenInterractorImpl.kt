package com.example.dimi.reactiveclean.domain.NewsMain.section_chosen

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen.SectionChosenRepository
import io.reactivex.Single
import javax.inject.Inject

class SectionChosenInterractorImpl
@Inject constructor(private val repositoty: SectionChosenRepository) : SectionChosenInterractor {
    override fun getSectionContent(page: Int): Single<List<Content>> {
        return repositoty.getSectionContent(page)
    }
}