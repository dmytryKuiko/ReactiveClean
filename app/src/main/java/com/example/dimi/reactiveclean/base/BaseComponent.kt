package com.example.dimi.reactiveclean.base

interface BaseComponent<T> {

    fun inject(context: T)
}