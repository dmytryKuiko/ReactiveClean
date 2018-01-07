package com.example.dimi.reactiveclean.models.sections

sealed class SectionDisplayable {
    class Section(val title: String, val url: String) : SectionDisplayable()
}