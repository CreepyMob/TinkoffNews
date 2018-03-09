package com.creepymob.mobile.tinkoffnews.di

import android.arch.persistence.room.Room
import android.content.Context
import com.creepymob.mobile.tinkoffnews.data.database.AppDatabase
import com.creepymob.mobile.tinkoffnews.data.database.NewsDao
import dagger.Module
import dagger.Provides


/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 20:37
 *
 */
@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "app_bd").build()

    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()
}