package com.example.dimi.reactiveclean.extensions.paginator

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import io.reactivex.Single

interface Paginator<T> : BaseDataPresenter<PaginatorModelResult<T>> {

    fun setCallback(callback: (page: Int) -> Single<List<T>>)

    fun refresh()

    fun loadNextPage()

    fun getSingleEvent(): LiveData<String>
}