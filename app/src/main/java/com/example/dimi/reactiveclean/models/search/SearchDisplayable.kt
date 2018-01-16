package com.example.dimi.reactiveclean.models.search

sealed class SearchDisplayable {

    class Search(
            val text: String,
            val dateTime: String
    ) : SearchDisplayable()
}