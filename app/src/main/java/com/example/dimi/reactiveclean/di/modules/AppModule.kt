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


@Module(subcomponents = [SplashComponent::class, TutorialComponent::class,
    NewsMainComponent::class])
abstract class AppModule {

    @Binds
    abstract internal fun bindSchedulersProvider(app: AppSchedulers): SchedulersProvider

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideContext(app: App): Context = app.applicationContext

        @JvmStatic
        @Provides
        @Singleton
        fun provideAppDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideSectionsDao(appDatabase: AppDatabase) = appDatabase.sectionsDao()

        @JvmStatic
        @Provides
        @Singleton
        fun provideContentDao(appDatabase: AppDatabase) = appDatabase.contentDao()

        @JvmStatic
        @Provides
        @Singleton
        fun provideRefWatcher(app: App): RefWatcher = LeakCanary.install(app)

        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
    }

}