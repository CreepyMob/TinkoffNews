package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.Single

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:51
 *
 */
class NewsDataSourceRemote(private val tinkoffApi: TinkoffApi) {

    fun getNews(): Single<List<NewsEntry>> = tinkoffApi.getNews()
}