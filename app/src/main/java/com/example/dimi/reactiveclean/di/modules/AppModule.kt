package com.example.dimi.reactiveclean.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.di.components.MainComponent
import com.example.dimi.reactiveclean.data.db.AppDatabase
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.di.components.SplashComponent
import com.example.dimi.reactiveclean.di.components.TutorialComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [SplashComponent::class, MainComponent::class, TutorialComponent::class,
    NewsMainComponent::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) = appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideSectionsDao(appDatabase: AppDatabase) = appDatabase.sectionsDao()

    @Provides
    @Singleton
    fun provideRefWatcher(app: App): RefWatcher = LeakCanary.install(app)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
}