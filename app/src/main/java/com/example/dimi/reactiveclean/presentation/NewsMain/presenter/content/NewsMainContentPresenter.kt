package com.example.dimi.reactiveclean.presentation.NewsMain.presenter.content

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.models.content.ContentRecyclerData
import io.reactivex.Observable

interface NewsMainContentPresenter : BaseDataPresenter<List<ContentDisplayable>> {

    fun getError(): LiveData<Unit>

    fun listenRecyclerScrollAndItems(lastVisibleAndAllItems: Observable<ContentRecyclerData>)

}