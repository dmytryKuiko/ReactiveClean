package com.example.dimi.reactiveclean.base

/**
 * Created by dimi on 09.12.2017.
 */
interface BasePresenter<T> {

    fun bindView(view: T)

    fun unbindView()
}