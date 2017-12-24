package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dimi.reactiveclean.base.BaseDao
import com.example.dimi.reactiveclean.models.content.Content
import io.reactivex.Flowable

@Dao
abstract class ContentDao : BaseDao<Content> {

    @Query("SELECT * FROM ${TableNames.CONTENT}")
    abstract fun getAllContent(): Flowable<List<Content>>
}