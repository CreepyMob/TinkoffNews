package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.data.database.NewsDao
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 20:40
 */
@RunWith(MockitoJUnitRunner::class)
class NewsDataSourceLocaleTest {

    private lateinit var dataSource: NewsDataSourceLocale

    @Mock private lateinit var newsDao: NewsDao
    private val newsResult: List<NewsEntry> = listOf(mock(), mock(), mock())

    @Before
    fun setUp() {
        dataSource = NewsDataSourceLocale(newsDao)

    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(newsDao)
    }

    @Test
    fun getNewsSuccess() {
        whenever(newsDao.getNews()).thenReturn(Single.just(newsResult))

        dataSource.getNews().test()
                .assertComplete()
                .assertValue(newsResult)

        inOrder(newsDao).apply {
            verify(newsDao).getNews()
        }
    }

    @Test
    fun getNewsError() {
        whenever(newsDao.getNews()).thenReturn(Single.just(listOf()))

        dataSource.getNews().test()
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)

        inOrder(newsDao).apply {
            verify(newsDao).getNews()
        }
    }

    @Test
    fun cacheNewsSuccess() {

        val news: List<NewsEntry> = listOf()
        val insertResult = mock<List<Long>>()
        val typedArray = news.toTypedArray()
        whenever(newsDao.storeNews(*typedArray)).thenReturn(insertResult)

        dataSource.cacheNews(news).test()
                .assertComplete()

        inOrder(newsDao).apply {
            verify(newsDao).storeNews(*typedArray)
        }
    }

    @Test
    fun clearCache() {
        whenever(newsDao.clear()).thenReturn(100)
        dataSource.clearCache().test()
                .assertComplete()

        inOrder(newsDao).apply {
            verify(newsDao).clear()
        }
    }

}