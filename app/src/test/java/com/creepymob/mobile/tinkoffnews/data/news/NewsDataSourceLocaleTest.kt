package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.SchedulersProviderMock
import com.creepymob.mobile.tinkoffnews.data.database.NewsDao
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.nhaarman.mockito_kotlin.*
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
    private lateinit var schedulersProvider: SchedulersProvider
    private val newsResult: List<NewsEntry> = listOf(mock(), mock(), mock())

    @Before
    fun setUp() {
        schedulersProvider = spy(SchedulersProviderMock())
        dataSource = NewsDataSourceLocale(newsDao, schedulersProvider)

    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(newsDao)
        verifyNoMoreInteractions(schedulersProvider)
    }

    @Test
    fun getNewsSuccess() {
        whenever(newsDao.getNews()).thenReturn(Single.just(newsResult))

        dataSource.getNews().test()
                .assertComplete()
                .assertValue(newsResult)

        inOrder(newsDao, schedulersProvider).apply {
            verify(newsDao).getNews()
            verify(schedulersProvider).single()
        }
    }

    @Test
    fun getNewsError() {
        whenever(newsDao.getNews()).thenReturn(Single.just(listOf()))

        dataSource.getNews().test()
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)

        inOrder(newsDao, schedulersProvider).apply {
            verify(newsDao).getNews()
            verify(schedulersProvider).single()
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

        inOrder(newsDao, schedulersProvider).apply {
            verify(schedulersProvider).single()
            verify(newsDao).storeNews(*typedArray)
        }
    }

    @Test
    fun clearCache() {
        whenever(newsDao.clear()).thenReturn(100)
        dataSource.clearCache().test()
                .assertComplete()

        inOrder(newsDao, schedulersProvider).apply {
            verify(schedulersProvider).single()
            verify(newsDao).clear()
        }
    }

}