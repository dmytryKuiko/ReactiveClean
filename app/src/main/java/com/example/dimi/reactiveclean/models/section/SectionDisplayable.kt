package com.example.dimi.reactiveclean.models.section

sealed class SectionDisplayable {
    class Section(val title: String, val id: String) : SectionDisplayable()
}