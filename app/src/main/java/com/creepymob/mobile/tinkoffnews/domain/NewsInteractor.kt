package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.Single

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:36
 *
 */
class NewsInteractor(private val repository: NewsRepository, private val datableSorter: DatableSorter) {

    fun loadNews(): Single<List<NewsEntry>> = repository.getNews().map { datableSorter.sort(it) }

    fun reloadNews(): Single<List<NewsEntry>> = repository.clearCache()
            .andThen(loadNews())
}