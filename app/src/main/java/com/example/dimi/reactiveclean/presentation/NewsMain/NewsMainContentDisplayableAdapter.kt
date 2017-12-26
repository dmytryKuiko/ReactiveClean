package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_content_dispalayable.view.*

class NewsMainContentDisplayableAdapter : AdapterDelegate<List<BaseItemDisplayable>>() {

    override fun isForViewType(itemDisplayables: List<BaseItemDisplayable>, position: Int): Boolean {
        return itemDisplayables[position] is ContentDisplayable
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_content_dispalayable, parent, false)
        return ContentDisplayableViewHolder(view)
    }

    override fun onBindViewHolder(itemDisplayable: List<BaseItemDisplayable>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = itemDisplayable[position] as? ContentDisplayable ?: throw ClassCastException("Item is not a ContentDisplayable")
        val contentDisplayableHolder = holder as? ContentDisplayableViewHolder ?: throw ClassCastException("not ContentDisplayable")
        contentDisplayableHolder.bind(item, position)
    }

    inner class ContentDisplayableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ContentDisplayable, position: Int) {
            with(itemView) {
                val text = "$position  ${item.title}"
                row_content_displayable_name_text_view.text = text
            }
        }
    }
}