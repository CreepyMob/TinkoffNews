package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import io.reactivex.Single

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:36
 *
 */
interface NewsDetailsRepository {

    fun getDetailsById(newsId: Long): Single<NewsEntryDetails>
}