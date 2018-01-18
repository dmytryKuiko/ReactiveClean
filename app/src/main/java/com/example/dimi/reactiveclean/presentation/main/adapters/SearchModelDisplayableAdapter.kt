package com.example.dimi.reactiveclean.presentation.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.search.SearchDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_search_displayable.view.*

class SearchModelDisplayableAdapter(
    private val callback: (SearchDisplayable.Search) -> Unit
) : AdapterDelegate<MutableList<SearchDisplayable>>() {

    override fun isForViewType(items: MutableList<SearchDisplayable>, position: Int): Boolean {
        return items[position] is SearchDisplayable.Search
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_search_displayable, parent, false)
        return SearchViewHolder(view, callback)
    }

    override fun onBindViewHolder(
        items: MutableList<SearchDisplayable>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as? SearchDisplayable.Search
                ?: throw ClassCastException("SearchDisplayable is not a Search")
        val contentDisplayableHolder =
            holder as? SearchViewHolder ?: throw ClassCastException("not a SearchViewHolder")
        contentDisplayableHolder.bind(item)
    }

    inner class SearchViewHolder(view: View, callback: (SearchDisplayable.Search) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private lateinit var search: SearchDisplayable.Search

        init {
            itemView.setOnClickListener { callback.invoke(search) }
        }

        fun bind(item: SearchDisplayable.Search) {
            search = item
            with(itemView) {
                row_search_displayable_text.text = item.text
            }
        }
    }
}