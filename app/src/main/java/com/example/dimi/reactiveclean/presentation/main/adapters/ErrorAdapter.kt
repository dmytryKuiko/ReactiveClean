package com.example.dimi.reactiveclean.presentation.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_error_displayable.view.*

class ErrorAdapter(
    private val refresh: () -> Unit
) : AdapterDelegate<MutableList<ContentDisplayable>>() {

    override fun isForViewType(items: MutableList<ContentDisplayable>, position: Int): Boolean {
        return items[position] is ContentDisplayable.Error
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_error_displayable, parent, false)
        return ErrorDisplayableViewHolder(view, refresh)
    }

    override fun onBindViewHolder(
        items: MutableList<ContentDisplayable>,
        position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>
    ) {
    }

    inner class ErrorDisplayableViewHolder(itemView: View, refresh: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.error_refresh.setOnClickListener { refresh.invoke() }
        }
    }
}