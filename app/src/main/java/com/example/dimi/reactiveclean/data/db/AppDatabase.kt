package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.Section

@Database(entities = [Article::class, Section::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun sectionsDao(): SectionsDao
}