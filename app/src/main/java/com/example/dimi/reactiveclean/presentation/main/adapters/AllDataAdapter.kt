package com.example.dimi.reactiveclean.presentation.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

class AllDataAdapter : AdapterDelegate<MutableList<ContentDisplayable>>() {

    override fun isForViewType(items: MutableList<ContentDisplayable>, position: Int): Boolean =
            items[position] is ContentDisplayable.Progress

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_all_content_displayable, parent, false)
        return AllDataViewHolder(view)
    }

    override fun onBindViewHolder(
            item: MutableList<ContentDisplayable>,
            position: Int,
            holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {}

    inner class AllDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}