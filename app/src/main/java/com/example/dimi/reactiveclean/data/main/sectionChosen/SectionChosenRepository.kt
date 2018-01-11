package com.example.dimi.reactiveclean.data.main.sectionChosen

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single

interface SectionChosenRepository {
    fun getSectionContent(page: Int): Single<List<Content>>
}