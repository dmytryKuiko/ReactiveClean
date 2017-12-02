package com.example.dimi.reactiveclean.base

import android.arch.persistence.room.*

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}