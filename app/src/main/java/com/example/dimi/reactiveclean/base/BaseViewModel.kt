package com.example.dimi.reactiveclean.base

import android.arch.lifecycle.LiveData

interface BaseViewModel<T> {

    fun getData(): LiveData<T>

    fun disposeSubscriptions()
}