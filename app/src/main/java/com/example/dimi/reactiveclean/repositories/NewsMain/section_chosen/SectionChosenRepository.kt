package com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Single

interface SectionChosenRepository {
    fun getSectionContent(page: Int): Single<List<Content>>
}