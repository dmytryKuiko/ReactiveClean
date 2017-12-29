package com.example.dimi.reactiveclean.presentation.NewsMain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import com.example.dimi.reactiveclean.extensions.visible
import com.example.dimi.reactiveclean.models.content.LoadingDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_loading_displayable.view.*

class LoadingDisplayableAdapter : AdapterDelegate<MutableList<BaseItemDisplayable>>() {

    override fun isForViewType(itemDisplayables: MutableList<BaseItemDisplayable>, position: Int): Boolean {
        return itemDisplayables[position] is LoadingDisplayable
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_loading_displayable, parent, false)
        return LoadingDisplayableViewHolder(view)
    }

    override fun onBindViewHolder(itemDisplayable: MutableList<BaseItemDisplayable>,
                                  position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {}

    inner class LoadingDisplayableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}