package com.example.dimi.reactiveclean.utils.paginator

import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.content.ContentState

class PaginatorData<T>(
        val content: List<T>,
        val state: ContentState,
        val recyclerUpdate: RecyclerUpdate
)