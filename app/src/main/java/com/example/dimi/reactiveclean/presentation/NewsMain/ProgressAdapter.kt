package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.content.Item
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

class ProgressAdapter : AdapterDelegate<MutableList<Item>>() {

    override fun isForViewType(items: MutableList<Item>, position: Int): Boolean {
        return items[position] is Item.Progress
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_loading_displayable, parent, false)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(item: MutableList<Item>,
                                  position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}