package com.example.dimi.reactiveclean.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.di.components.MainActivitySubcomponent
import com.example.dimi.reactiveclean.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [(MainActivitySubcomponent::class)])
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
}