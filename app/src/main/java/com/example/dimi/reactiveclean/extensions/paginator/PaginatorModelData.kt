package com.example.dimi.reactiveclean.extensions.paginator

import com.example.dimi.reactiveclean.models.RecyclerUpdate
import com.example.dimi.reactiveclean.models.content.ContentState

class PaginatorModelData<T>(
    val content: List<T>,
    val state: ContentState,
    val recyclerUpdate: RecyclerUpdate
)