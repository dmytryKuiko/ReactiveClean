package com.example.dimi.reactiveclean.domain.main.sectionChosen

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single

interface SectionChosenInteractor {

    fun getSectionContent(page: Int): Single<List<Content>>
}