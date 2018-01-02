package com.example.dimi.reactiveclean.models.content

import com.example.dimi.reactiveclean.models.RecyclerUpdate

data class ContentDisplayable(
        val content: List<Item>,
        val state: ContentState,
        val recyclerUpdate: RecyclerUpdate
)