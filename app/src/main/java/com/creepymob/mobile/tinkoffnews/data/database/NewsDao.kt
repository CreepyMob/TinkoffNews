package com.creepymob.mobile.tinkoffnews.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.creepymob.mobile.tinkoffnews.entity.TABLE_NEWS_ENTRY
import io.reactivex.Single


/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 20:34
 *
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM $TABLE_NEWS_ENTRY")
    fun getNews(): Single<List<NewsEntry>>

    @Insert
    fun storeNews(vararg news: NewsEntry): List<Long>

    @Query("DELETE FROM $TABLE_NEWS_ENTRY")
    fun clear(): Int

}