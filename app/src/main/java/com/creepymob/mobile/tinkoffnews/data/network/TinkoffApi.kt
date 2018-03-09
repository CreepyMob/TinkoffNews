package com.creepymob.mobile.tinkoffnews.data.network

import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 17:28
 *
 */
interface TinkoffApi {

    @GET("news")
    fun getNews(): Single<List<NewsEntry>>

    @GET("news_content")
    fun getNewsDetails(@Query("id") id: Long): Single<NewsEntryDetails>

}