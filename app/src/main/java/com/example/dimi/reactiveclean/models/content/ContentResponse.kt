package com.example.dimi.reactiveclean.models.content

/**
 * A class that represents an answer from the server
 */
class ContentResponse(
    val status: String?,
    val total: Int?,
    val startIndex: Int?,
    val pageSize: Int?,
    val currentPage: Int?,
    val pages: Int?,
    val orderBy: String?,
    val results: List<SingleContentResponse>?
)