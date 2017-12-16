package com.example.dimi.reactiveclean.base

import android.arch.lifecycle.LiveData

interface BaseDataPresenter<T> : BasePresenter {

    fun getData(): LiveData<T>
}