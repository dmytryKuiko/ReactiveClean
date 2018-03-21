package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable

/**
 * Defines querries for ContentTable
 */
@Dao
abstract class ContentDao : BaseDao<Content> {

    @Query("SELECT * FROM ${TableNames.CONTENT}")
    abstract fun getAllContent(): Flowable<List<Content>>

    @Query("DELETE FROM ${TableNames.CONTENT}")
    abstract fun deleteAllArticles()

    @Transaction
    open fun deleteAllArticlesAndInsertAll(list: List<Content>) {
        deleteAllArticles()
        insert(list.toTypedArray())
    }
}