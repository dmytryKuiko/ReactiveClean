package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.models.content.ErrorDisplayable
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_error_displayable.view.*
import kotlinx.android.synthetic.main.row_loading_displayable.view.*

class ErrorDisplayableAdapter : AdapterDelegate<List<BaseItemDisplayable>>() {

    override fun isForViewType(itemDisplayables: List<BaseItemDisplayable>, position: Int): Boolean {
        return itemDisplayables[position] is ErrorDisplayable
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_error_displayable, parent, false)
        return ErrorDisplayableViewHolder(view)
    }

    override fun onBindViewHolder(itemDisplayable: List<BaseItemDisplayable>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = itemDisplayable[position] as? ErrorDisplayable ?: throw ClassCastException("Item is not a ErrorDisplayable")
        val loadingDisplayableHolder = holder as? ErrorDisplayableViewHolder ?: throw ClassCastException("not ErrorDisplayable")
        loadingDisplayableHolder.bind(item)
    }

    inner class ErrorDisplayableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ErrorDisplayable) {
            with(itemView) {
                if(item.showError) {
                    row_error_displayable_text_view.visibility = View.VISIBLE
                } else {
                    row_error_displayable_text_view.visibility = View.INVISIBLE
                }
            }
        }
    }
}