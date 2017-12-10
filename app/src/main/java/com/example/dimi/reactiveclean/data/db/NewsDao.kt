package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.example.dimi.reactiveclean.base.BaseDao
import com.example.dimi.reactiveclean.models.Article
import io.reactivex.Flowable

@Dao
abstract class NewsDao : BaseDao<Article> {

    @Query("SELECT * FROM table_article")
    abstract fun getAllResponseData(): Flowable<List<Article>>

    @Query("SELECT * FROM table_article WHERE id = :id")
    abstract fun getSingleResponseData(id: Long): Flowable<Article>

    @Query("DELETE FROM table_article")
    abstract fun deleteAllArticles()

    @Transaction
    open fun deleteAllArticlesAndInsertAll(list: List<Article>) {
        deleteAllArticles()
        insert(list.toTypedArray())
    }
}