package com.example.dimi.reactiveclean.utils

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.example.dimi.reactiveclean.models.content.ContentDisplayable

class DiffUtilContent(private val oldList: List<BaseItemDisplayable>,
                      private val newList: List<BaseItemDisplayable>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is ContentDisplayable && newItem is ContentDisplayable) {
            oldItem.title == newItem.title
        } else {
            oldItem is LoadingDisplayable && newItem is LoadingDisplayable
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}