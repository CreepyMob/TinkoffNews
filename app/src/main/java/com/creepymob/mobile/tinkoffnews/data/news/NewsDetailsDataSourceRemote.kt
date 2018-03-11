package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import io.reactivex.Single

/**
 * User: andrey
 * Date: 10.03.2018
 * Time: 0:34
 *
 */
class NewsDetailsDataSourceRemote(private val tinkoffApi: TinkoffApi) {

    fun getDetailsById(newsId: Long): Single<NewsEntryDetails> = tinkoffApi.getNewsDetails(newsId)
}