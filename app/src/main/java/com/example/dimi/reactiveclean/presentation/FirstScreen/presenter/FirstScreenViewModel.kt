package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import android.arch.lifecycle.LiveData

interface FirstScreenViewModel<Data> {

    fun getData(): LiveData<Data>

    fun getProgress(): LiveData<Int>

    fun getError(): LiveData<Boolean>

    fun onRefreshClicked()
}