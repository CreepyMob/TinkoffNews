package com.creepymob.mobile.tinkoffnews.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 20:37
 *
 */
@Database(entities = [NewsEntry::class], version = 1)
@TypeConverters(value = [(DataConverter::class)])
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}