package com.example.dimi.reactiveclean.base

interface BasePresenter<T> {

    fun bindView(view: T)

    fun unbindView()
}