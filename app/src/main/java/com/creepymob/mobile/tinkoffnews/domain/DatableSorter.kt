package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.Datable

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 15:31
 *
 */
class DatableSorter {

    fun <T : Datable> sort(news: List<T>, sortType: SortType = SortType.DESK): List<T> {
        return when (sortType) {
            SortType.ASK -> news.sortedBy { datable: Datable -> datable.date.time }
            SortType.DESK -> news.sortedByDescending { datable: Datable -> datable.date.time }
        }
    }
}

enum class SortType {
    ASK, DESK,
}

