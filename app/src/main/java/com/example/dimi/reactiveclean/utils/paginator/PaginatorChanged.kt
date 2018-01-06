package com.example.dimi.reactiveclean.utils.paginator

import com.example.dimi.reactiveclean.base.BaseDataPresenter
import io.reactivex.Completable
import io.reactivex.Flowable

interface PaginatorChanged<T> : BaseDataPresenter<PaginatorResult<T>> {

    fun setCallbacks(init: () -> Completable,
                     database: () -> Flowable<List<T>>,
                     nextData: (Int) -> Completable)

    fun refresh()

    fun loadNewPage()
}