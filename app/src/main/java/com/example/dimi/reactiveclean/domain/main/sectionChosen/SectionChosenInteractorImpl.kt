package com.example.dimi.reactiveclean.domain.main.sectionChosen

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepository
import io.reactivex.Single
import javax.inject.Inject

class SectionChosenInteractorImpl
@Inject constructor(private val repository: SectionChosenRepository) : SectionChosenInteractor {

    override fun getSectionContent(page: Int): Single<List<Content>> {
        return repository.getSectionContent(page)
    }
}