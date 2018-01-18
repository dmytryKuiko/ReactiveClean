package com.example.dimi.reactiveclean.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.data.db.AppDatabase
import com.example.dimi.reactiveclean.di.components.NewsMainComponent
import com.example.dimi.reactiveclean.di.components.SplashComponent
import com.example.dimi.reactiveclean.di.components.TutorialComponent
import com.example.dimi.reactiveclean.utils.AppSchedulers
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [SplashComponent::class, TutorialComponent::class, NewsMainComponent::class])
abstract class AppModule {

    @Binds
    internal abstract fun bindSchedulersProvider(app: AppSchedulers): SchedulersProvider

    @Module
    companion object {

        @JvmStatic
        @Singleton
        @Provides
        fun provideContext(app: App): Context = app.applicationContext

        @JvmStatic
        @Singleton
        @Provides
        fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideSectionsDao(appDatabase: AppDatabase) = appDatabase.sectionsDao()

        @JvmStatic
        @Singleton
        @Provides
        fun provideContentDao(appDatabase: AppDatabase) = appDatabase.contentDao()

        @JvmStatic
        @Singleton
        @Provides
        fun provideSearchDao(appDatabase: AppDatabase) = appDatabase.searchDao()

        @JvmStatic
        @Singleton
        @Provides
        fun provideRefWatcher(app: App): RefWatcher = LeakCanary.install(app)

        @JvmStatic
        @Singleton
        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
    }
}