package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable

interface ContentStore {

    fun storeAll(list: List<Content>)

    fun getAll(): Flowable<List<Content>>

    fun deleteAllAndStoreAll(valueList: List<Content>)

}