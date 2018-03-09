package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import junit.framework.TestCase.assertSame
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 16:49
 */
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    private lateinit var repository: NewsRepositoryImpl

    @Mock private lateinit var newsDataSourceRemote: NewsDataSourceRemote
    @Mock private lateinit var newsDataSourceLocale: NewsDataSourceLocale

    @Before
    fun setUp() {
        repository = NewsRepositoryImpl(newsDataSourceLocale, newsDataSourceRemote)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(newsDataSourceRemote)
        verifyNoMoreInteractions(newsDataSourceLocale)
    }

    @Test
    fun getNewsFromLocale() {

        val localeNews = mock<List<NewsEntry>>()
        val remoteNews = mock<List<NewsEntry>>()
        whenever(newsDataSourceLocale.getNews()).thenReturn(Single.just(localeNews))
        whenever(newsDataSourceRemote.getNews()).thenReturn(Single.just(remoteNews))

        repository.getNews().test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(localeNews)

        inOrder(newsDataSourceLocale, newsDataSourceRemote).apply {
            verify(newsDataSourceLocale).getNews()
            verify(newsDataSourceRemote).getNews()
        }
    }

    @Test
    fun getNewsFromRemote() {

        val exception = RuntimeException()
        val remoteNews = mock<List<NewsEntry>>()
        whenever(newsDataSourceLocale.getNews()).thenReturn(Single.error(exception))
        whenever(newsDataSourceRemote.getNews()).thenReturn(Single.just(remoteNews))
        whenever(newsDataSourceLocale.cacheNews(remoteNews)).thenReturn(Completable.complete())

        repository.getNews().test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(remoteNews)

        inOrder(newsDataSourceLocale, newsDataSourceRemote).apply {
            verify(newsDataSourceLocale).getNews()
            verify(newsDataSourceRemote).getNews()
            verify(newsDataSourceLocale).cacheNews(remoteNews)
        }
    }

    @Test
    fun clearCache() {
        val result = mock<Completable>()
        whenever(newsDataSourceLocale.clearCache()).thenReturn(result)

        assertSame(result, repository.clearCache())

        inOrder(newsDataSourceLocale).apply {
            verify(newsDataSourceLocale).clearCache()
        }
    }

}