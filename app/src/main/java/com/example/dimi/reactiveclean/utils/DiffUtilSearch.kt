package com.example.dimi.reactiveclean.utils

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.search.SearchDisplayable

class DiffUtilSearch(private val oldList: List<SearchDisplayable>,
                     private val newList: List<SearchDisplayable>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is SearchDisplayable.Search && newItem is SearchDisplayable.Search) {
            oldItem.text == newItem.text
        } else {
            TODO()
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is SearchDisplayable.Search && newItem is SearchDisplayable.Search) {
            oldItem.text == newItem.text
        } else {
            TODO()
        }
    }
}