package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.dimi.reactiveclean.models.section.Section
import io.reactivex.Flowable

/**
 * Defines querries for SectionTable
 */
@Dao
abstract class SectionsDao : BaseDao<Section> {

    @Query("SELECT * FROM ${TableNames.SECTIONS}")
    abstract fun getAllSections(): Flowable<List<Section>>

    @Query("SELECT * FROM ${TableNames.SECTIONS} WHERE section_name LIKE '%' || :params || '%'")
    abstract fun getSpecificSections(params: String): Flowable<List<Section>>
}