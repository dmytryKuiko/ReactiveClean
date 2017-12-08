package com.example.dimi.reactiveclean.presentation.FirstScreen.view_model

import android.arch.lifecycle.LiveData

interface FirstScreenViewModel<Err, Data> {

    fun provideData(): LiveData<Data>

    fun provideError(): LiveData<Err>
}