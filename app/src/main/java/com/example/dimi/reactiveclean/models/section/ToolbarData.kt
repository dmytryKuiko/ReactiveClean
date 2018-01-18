package com.example.dimi.reactiveclean.models.section

import com.example.dimi.reactiveclean.R

sealed class ToolbarData(
        val navIcon: Int,
        val searchVisibility: Boolean,
        val title: String
) {

    class SectionToolbar(
            navIcon: Int = R.drawable.ic_drawer_24dp,
            searchVisibility: Boolean = false,
            title: String
    ) : ToolbarData(navIcon, searchVisibility, title)

    class SearchToolbar(
            navIcon: Int = R.drawable.ic_arrow_back,
            searchVisibility: Boolean = true,
            title: String
    ) : ToolbarData(navIcon, searchVisibility, title)
}