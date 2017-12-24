package com.example.dimi.reactiveclean.models.content

class ContentResponse(val status: String?,
                      val total: Int?,
                      val startIndex: Int?,
                      val pageSize: Int?,
                      val currentPage: Int?,
                      val pages: Int?,
                      val orderBy: String?,
                      val results: List<SingleContentResponse>?) {
}