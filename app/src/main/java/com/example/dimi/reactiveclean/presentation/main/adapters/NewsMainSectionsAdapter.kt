package com.example.dimi.reactiveclean.presentation.main.adapters

import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class NewsMainSectionsAdapter(
    openCurrentSection: (SectionDisplayable.Section) -> Unit
) : ListDelegationAdapter<MutableList<SectionDisplayable>>() {

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(NewsMainSectionsDisplayableAdapter(openCurrentSection))
    }

    fun setNewData(list: List<SectionDisplayable>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}