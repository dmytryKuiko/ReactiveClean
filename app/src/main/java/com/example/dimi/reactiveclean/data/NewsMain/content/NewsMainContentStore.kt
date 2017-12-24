package com.example.dimi.reactiveclean.data.NewsMain.content

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable

interface NewsMainContentStore {

    fun storeAll(list: List<Content>)

    fun getAll(): Flowable<List<Content>>
}