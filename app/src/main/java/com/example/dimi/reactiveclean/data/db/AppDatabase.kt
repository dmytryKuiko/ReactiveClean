package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.sections.Section

@Database(entities = [Article::class, Section::class, Content::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun sectionsDao(): SectionsDao

    abstract fun contentDao(): ContentDao
}