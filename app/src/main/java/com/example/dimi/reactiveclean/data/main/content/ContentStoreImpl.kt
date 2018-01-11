package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.db.ContentDao
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable
import javax.inject.Inject

class ContentStoreImpl
@Inject constructor(private val contentDao: ContentDao): ContentStore {

    override fun storeAll(list: List<Content>) =
            contentDao.insert(list.toTypedArray())

    override fun getAll(): Flowable<List<Content>> =
            contentDao.getAllContent()

    override fun deleteAllAndStoreAll(valueList: List<Content>) =
            contentDao.deleteAllArticlesAndInsertAll(valueList)
}