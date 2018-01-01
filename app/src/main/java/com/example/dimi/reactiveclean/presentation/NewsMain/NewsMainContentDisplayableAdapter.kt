package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.content.Item
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_content_dispalayable.view.*

class NewsMainContentDisplayableAdapter : AdapterDelegate<MutableList<Item>>() {

    override fun isForViewType(items: MutableList<Item>, position: Int): Boolean {
        return items[position] is Item.Content
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_content_dispalayable, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(items: MutableList<Item>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = items[position] as? Item.Content ?: throw ClassCastException("Item is not a Content")
        val contentDisplayableHolder = holder as? ContentViewHolder ?: throw ClassCastException("not a ContentHolder")
        contentDisplayableHolder.bind(item, position)
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item.Content, position: Int) {
            with(itemView) {
                val text = "$position  ${item.title}"
                row_content_displayable_name_text_view.text = text
            }
        }
    }
}