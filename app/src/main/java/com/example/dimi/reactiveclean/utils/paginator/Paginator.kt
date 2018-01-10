package com.example.dimi.reactiveclean.utils.paginator

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import io.reactivex.Single

interface Paginator<T> : BaseDataPresenter<PaginatorResult<T>> {

    fun setCallback(callback: (page: Int) -> Single<List<T>>)

    fun refresh()

    fun loadNextPage()

    fun getSingleEvent(): LiveData<String>
}