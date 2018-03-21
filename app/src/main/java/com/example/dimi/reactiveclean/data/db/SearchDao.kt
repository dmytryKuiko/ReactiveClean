package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dimi.reactiveclean.models.search.SearchModel
import io.reactivex.Flowable

/**
 * Defines querries for SearchTable
 */
@Dao
abstract class SearchDao : BaseDao<SearchModel> {

    @Query("SELECT * FROM ${TableNames.SEARCH} order by id desc LIMIT :count")
    abstract fun getSearches(count: Int): Flowable<List<SearchModel>>
}