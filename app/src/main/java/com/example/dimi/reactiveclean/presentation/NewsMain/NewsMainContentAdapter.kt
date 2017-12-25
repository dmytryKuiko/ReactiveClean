package com.example.dimi.reactiveclean.presentation.NewsMain

import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.models.LoadingDisplayable
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class NewsMainContentAdapter(val list: List<BaseItemDisplayable>) : ListDelegationAdapter<List<BaseItemDisplayable>>() {

    init {
        delegatesManager.addDelegate(NewsMainContentDisplayableAdapter())
        delegatesManager.addDelegate(LoadingDisplayableAdapter())
        val mutableList: MutableList<BaseItemDisplayable> = mutableListOf()
        mutableList.addAll(list)
        mutableList.add(LoadingDisplayable(true))
        setItems(mutableList)
    }

    fun updateItems(list: List<BaseItemDisplayable>) {
//        val mutableList: MutableList<BaseItemDisplayable> = mutableListOf()
//        mutableList.addAll(list)
//        mutableList.add(LoadingDisplayable(true))
        setItems(list)
    }
}