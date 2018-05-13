package com.example.dimi.reactiveclean.utils

import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.models.content.ContentDisplayable

class DiffUtilCallbackContent : DiffUtil.ItemCallback<ContentDisplayable>() {
    override fun areItemsTheSame(
        oldItem: ContentDisplayable?,
        newItem: ContentDisplayable?
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ContentDisplayable?,
        newItem: ContentDisplayable?
    ): Boolean =
        if (oldItem is ContentDisplayable.Content && newItem is ContentDisplayable.Content) {
            oldItem.title == newItem.title
        } else {
            oldItem is ContentDisplayable.Progress && newItem is ContentDisplayable.Progress
                    || oldItem is ContentDisplayable.Error && newItem is ContentDisplayable.Error
        }
}