package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import io.reactivex.Single

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 23:52
 *
 */
class NewsDetailsInteractor(private val id: Long, private val repository: NewsDetailsRepository) {

    fun getDetails(): Single<NewsEntryDetails> = repository.getDetailsById(id)

}