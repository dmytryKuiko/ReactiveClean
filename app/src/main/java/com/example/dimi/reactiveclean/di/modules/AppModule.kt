package com.example.dimi.reactiveclean.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.di.components.FirstScreenComponent
import com.example.dimi.reactiveclean.data.db.AppDatabase
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [(FirstScreenComponent::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "reddit_db").build()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideRefWatcher(app: App): RefWatcher = LeakCanary.install(app)
}