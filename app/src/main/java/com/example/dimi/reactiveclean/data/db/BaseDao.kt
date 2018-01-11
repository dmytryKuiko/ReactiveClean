package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.*

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(array: Array<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}