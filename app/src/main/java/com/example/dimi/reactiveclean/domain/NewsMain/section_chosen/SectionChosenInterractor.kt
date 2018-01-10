package com.example.dimi.reactiveclean.domain.NewsMain.section_chosen

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single

interface SectionChosenInterractor {

    fun getSectionContent(page: Int): Single<List<Content>>
}