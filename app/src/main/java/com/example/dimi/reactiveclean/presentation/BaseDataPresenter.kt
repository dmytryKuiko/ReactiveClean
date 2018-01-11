package com.example.dimi.reactiveclean.presentation

import android.arch.lifecycle.LiveData

interface BaseDataPresenter<T> : BasePresenter {

    fun getData(): LiveData<T>
}