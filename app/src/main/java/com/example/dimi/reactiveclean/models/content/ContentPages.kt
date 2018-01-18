package com.example.dimi.reactiveclean.models.content

data class ContentPages(
    val status: String = "initial",
    val total: Int = 0,
    val startIndex: Int = 0,
    val pageSize: Int = 0,
    val currentPage: Int = 0,
    val pages: Int = 0
)