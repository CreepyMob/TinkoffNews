package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
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
 * Date: 10.03.2018
 * Time: 0:28
 */
@RunWith(MockitoJUnitRunner::class)
class NewsDetailsDataSourceRemoteTest {

    private lateinit var dataSource: NewsDetailsDataSourceRemote
    @Mock private lateinit var tinkoffApi: TinkoffApi
    @Mock private lateinit var detailsResponse: Single<NewsEntryDetails>
    private val newsId = 100L

    @Before
    fun setUp() {
        dataSource = NewsDetailsDataSourceRemote(tinkoffApi)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(tinkoffApi)
    }

    @Test
    fun getDetailsById() {
        whenever(tinkoffApi.getNewsDetails(newsId)).thenReturn(detailsResponse)

        assertSame(detailsResponse, dataSource.getDetailsById(newsId))

        verify(tinkoffApi).getNewsDetails(newsId)
    }

}