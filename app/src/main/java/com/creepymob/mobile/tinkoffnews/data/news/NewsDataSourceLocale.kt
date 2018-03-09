package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.data.database.NewsDao
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.Completable
import io.reactivex.Single

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:51
 *
 */
class NewsDataSourceLocale(private val newsDao: NewsDao, private val schedulersProvider: SchedulersProvider) {

    fun getNews(): Single<List<NewsEntry>> = newsDao.getNews()
            .filter { it.isNotEmpty() }
            .toSingle()
            .subscribeOn(schedulersProvider.single())

    fun cacheNews(news: List<NewsEntry>): Completable = Completable.fromAction { newsDao.storeNews(*news.toTypedArray()) }
            .subscribeOn(schedulersProvider.single())

    fun clearCache(): Completable = Completable.fromAction { newsDao.clear() }
            .subscribeOn(schedulersProvider.single())

}