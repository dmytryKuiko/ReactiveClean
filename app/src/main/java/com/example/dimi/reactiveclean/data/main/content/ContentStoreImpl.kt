package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.db.ContentDao
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable
import javax.inject.Inject

class ContentStoreImpl
@Inject constructor(private val contentDao: ContentDao) : ContentStore {

    /**
     * Stores all Contents into Database
     * @param list data which has to be stored
     */
    override fun storeAll(list: List<Content>) =
        contentDao.insert(list.toTypedArray())

    /**
     * Retrieves all Content from Database
     * @return stored data in Database
     */
    override fun getAll(): Flowable<List<Content>> =
        contentDao.getAllContent()

    /**
     * Deletes existed Content in DB and Stores a new one
     * @param valueList data to be stored
     */
    override fun deleteAllAndStoreAll(valueList: List<Content>) =
        contentDao.deleteAllArticlesAndInsertAll(valueList)
}