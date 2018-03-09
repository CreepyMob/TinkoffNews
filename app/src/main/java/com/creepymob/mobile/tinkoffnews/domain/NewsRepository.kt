package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.Completable
import io.reactivex.Single

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:36
 *
 */
interface NewsRepository {

    fun getNews(): Single<List<NewsEntry>>

    fun clearCache(): Completable
}