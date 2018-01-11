package com.example.dimi.reactiveclean.utils.paginator

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import io.reactivex.Completable
import io.reactivex.Flowable

interface PaginatorDB<T> : BaseDataPresenter<PaginatorModelResult<T>> {

    fun setCallbacks(init: () -> Completable,
                     database: () -> Flowable<List<T>>,
                     nextData: (Int) -> Completable)

    fun refresh()

    fun loadNewPage()

    fun getSingleEvent(): LiveData<String>
}