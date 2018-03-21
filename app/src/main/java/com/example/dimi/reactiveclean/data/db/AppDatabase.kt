package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.search.SearchModel
import com.example.dimi.reactiveclean.models.section.Section

/**
 * Declares tables which will be used by Room
 */
@Database(entities = [Section::class, Content::class, SearchModel::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sectionsDao(): SectionsDao

    abstract fun contentDao(): ContentDao

    abstract fun searchDao(): SearchDao
}