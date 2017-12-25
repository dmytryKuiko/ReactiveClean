package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import android.support.v7.util.DiffUtil
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.base.BaseItemDisplayable
import io.reactivex.Observable

interface NewsMainContentPresenter : BaseDataPresenter<Pair<List<BaseItemDisplayable>, DiffUtil.DiffResult>> {

    fun getError(): LiveData<Unit>

    fun listenRecyclerLastVisiblePosition(listener: Observable<Int>)
}