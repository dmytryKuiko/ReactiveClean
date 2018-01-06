package com.example.dimi.reactiveclean.models.content

/**
 * Classes that represents items in PaginatorData content for different delegate adapters
 */
sealed class ContentDisplayable {

    class Content(val title: String, val url: String, val time: String, val sectionName: String) : ContentDisplayable()

    class Progress : ContentDisplayable()

    class Error : ContentDisplayable()

}