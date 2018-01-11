package com.example.dimi.reactiveclean.di

interface BaseComponent<T> {

    fun inject(context: T)
}