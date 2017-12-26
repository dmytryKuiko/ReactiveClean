package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_loading_displayable.view.*

class LoadingDisplayableAdapter : AdapterDelegate<List<BaseItemDisplayable>>() {

    override fun isForViewType(itemDisplayables: List<BaseItemDisplayable>, position: Int): Boolean {
        return itemDisplayables[position] is LoadingDisplayable
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_loading_displayable, parent, false)
        return LoadingDisplayableViewHolder(view)
    }

    override fun onBindViewHolder(itemDisplayable: List<BaseItemDisplayable>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val item = itemDisplayable[position] as? LoadingDisplayable ?: throw ClassCastException("Item is not a LoadingDisplayable")
        val loadingDisplayableHolder = holder as? LoadingDisplayableViewHolder ?: throw ClassCastException("not LoadingDisplayable")
        loadingDisplayableHolder.bind(item)
    }

    inner class LoadingDisplayableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: LoadingDisplayable) {
            with(itemView) {
                if(item.showLoading) {
                    row_loading_displayable_progress_bar.visibility = View.VISIBLE
                } else {
                    row_loading_displayable_progress_bar.visibility = View.INVISIBLE
                }
            }
        }
    }
}