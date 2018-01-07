package com.example.dimi.reactiveclean.utils.paginator

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.SingleEventLiveData
import io.reactivex.Completable
import io.reactivex.Flowable

interface PaginatorChanged<T> : BaseDataPresenter<PaginatorResult<T>> {

    fun setCallbacks(init: () -> Completable,
                     database: () -> Flowable<List<T>>,
                     nextData: (Int) -> Completable)

    fun refresh()

    fun loadNewPage()

    fun getSingleEvent(): LiveData<String>
}