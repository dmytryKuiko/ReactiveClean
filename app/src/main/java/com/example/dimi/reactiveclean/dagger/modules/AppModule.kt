package com.example.dimi.reactiveclean.dagger.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.dagger.components.MainActivitySubcomponent
import com.example.dimi.reactiveclean.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [(MainActivitySubcomponent::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "user-db").build()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
}