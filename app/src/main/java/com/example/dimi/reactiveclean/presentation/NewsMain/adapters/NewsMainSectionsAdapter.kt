package com.example.dimi.reactiveclean.presentation.NewsMain.adapters

import com.example.dimi.reactiveclean.models.sections.SectionDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class NewsMainSectionsAdapter(openCurrentSection: (SectionDisplayable.Section) -> Unit)
    : ListDelegationAdapter<MutableList<SectionDisplayable>>() {

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