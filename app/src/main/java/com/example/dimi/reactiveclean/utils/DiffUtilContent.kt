package com.example.dimi.reactiveclean.utils

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.models.content.ContentDisplayable

class DiffUtilContent(private val oldList: List<ContentDisplayable>,
                      private val newList: List<ContentDisplayable>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is ContentDisplayable.Content && newItem is ContentDisplayable.Content) {
            oldItem.title == newItem.title
        } else {
            oldItem is ContentDisplayable.Progress && newItem is ContentDisplayable.Progress || oldItem is ContentDisplayable.Error && newItem is ContentDisplayable.Error
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is ContentDisplayable.Content && newItem is ContentDisplayable.Content) {
            oldItem.title == newItem.title
        } else {
            oldItem is ContentDisplayable.Progress && newItem is ContentDisplayable.Progress || oldItem is ContentDisplayable.Error && newItem is ContentDisplayable.Error
        }
    }
}