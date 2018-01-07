package com.example.dimi.reactiveclean.presentation.NewsMain.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_section_displayable.view.*

class NewsMainSectionsDisplayableAdapter(private val callback: (SectionDisplayable.Section) -> Unit)
    : AdapterDelegate<MutableList<SectionDisplayable>>() {

    override fun isForViewType(items: MutableList<SectionDisplayable>, position: Int): Boolean {
        return items[position] is SectionDisplayable.Section
    }

    override fun onBindViewHolder(items: MutableList<SectionDisplayable>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = items[position] as? SectionDisplayable.Section ?: throw ClassCastException("ContentDisplayable is not a Content")
        val contentDisplayableHolder = holder as? SectionViewHolder ?: throw ClassCastException("not a ContentHolder")
        contentDisplayableHolder.bind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_section_displayable, parent, false)
        return SectionViewHolder(view, callback)
    }

    inner class SectionViewHolder(view: View, callback: (SectionDisplayable.Section) -> Unit) : RecyclerView.ViewHolder(view) {

        private lateinit var section: SectionDisplayable.Section

        init {
            itemView.setOnClickListener { callback.invoke(section) }
        }

        fun bind(item: SectionDisplayable.Section, position: Int) {
            section = item
            with(itemView) {
                row_sections_displayable_number.text = "$position"
                row_sections_displayable_title.text = item.title
            }
        }
    }
}