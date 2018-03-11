package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsRepository
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import io.reactivex.Single

/**
 * User: andrey
 * Date: 10.03.2018
 * Time: 0:28
 *
 */
class NewsDetailsRepositoryImpl(private val newsDetailsDataSourceRemote: NewsDetailsDataSourceRemote) : NewsDetailsRepository {

    override fun getDetailsById(newsId: Long): Single<NewsEntryDetails> = newsDetailsDataSourceRemote.getDetailsById(newsId)
}