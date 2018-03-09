package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.domain.NewsRepository
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.Completable
import io.reactivex.Single

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 15:22
 *
 */
class NewsRepositoryImpl(private val newsDataSourceLocale: NewsDataSourceLocale, private val newsDataSourceRemote: NewsDataSourceRemote) : NewsRepository {

    override fun getNews(): Single<List<NewsEntry>> = newsDataSourceLocale.getNews()
            .onErrorResumeNext(newsDataSourceRemote.getNews()
                    .flatMap {
                        newsDataSourceLocale.cacheNews(it)
                                .andThen(Single.just(it))
                    })

    override fun clearCache(): Completable = newsDataSourceLocale.clearCache()
}