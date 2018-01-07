package com.example.dimi.reactiveclean.presentation.NewsMain.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_content_dispalayable.view.*

class NewsMainContentDisplayableAdapter(private val callback: (ContentDisplayable.Content) -> Unit) : AdapterDelegate<MutableList<ContentDisplayable>>() {

    override fun isForViewType(items: MutableList<ContentDisplayable>, position: Int): Boolean {
        return items[position] is ContentDisplayable.Content
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_content_dispalayable, parent, false)
        return ContentViewHolder(view, callback)
    }

    override fun onBindViewHolder(items: MutableList<ContentDisplayable>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = items[position] as? ContentDisplayable.Content ?: throw ClassCastException("ContentDisplayable is not a Content")
        val contentDisplayableHolder = holder as? ContentViewHolder ?: throw ClassCastException("not a ContentHolder")
        contentDisplayableHolder.bind(item, position)
    }

    inner class ContentViewHolder(itemView: View, private val clickListener: (ContentDisplayable.Content) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private lateinit var content: ContentDisplayable.Content

        init {
            itemView.setOnClickListener { clickListener.invoke(content) }
        }
        fun bind(item: ContentDisplayable.Content, position: Int) {
            content = item
            with(itemView) {
                val text = "$position  ${item.title}"
                row_content_displayable_title.text = text
            }
        }
    }
}