package com.example.dimi.reactiveclean.utils

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.models.content.Item

class DiffUtilContent(private val oldList: List<Item>,
                      private val newList: List<Item>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is Item.Content && newItem is Item.Content) {
            oldItem.title == newItem.title
        } else {
            oldItem is Item.Progress && newItem is Item.Progress || oldItem is Item.Error && newItem is Item.Error
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is Item.Content && newItem is Item.Content) {
            oldItem.title == newItem.title
        } else {
            oldItem is Item.Progress && newItem is Item.Progress || oldItem is Item.Error && newItem is Item.Error
        }
    }
}