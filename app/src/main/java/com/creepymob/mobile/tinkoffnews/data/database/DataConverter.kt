package com.creepymob.mobile.tinkoffnews.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 21:08
 *
 */
class DataConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(value) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}