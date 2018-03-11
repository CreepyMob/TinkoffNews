package com.creepymob.mobile.tinkoffnews.data.news

import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsRepository
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
class NewsDetailsRepositoryImplTest {

    private lateinit var repository: NewsDetailsRepository
    @Mock private lateinit var dataSource: NewsDetailsDataSourceRemote
    @Mock private lateinit var detailsResponse: Single<NewsEntryDetails>
    private val newsId = 100L

    @Before
    fun setUp() {
        repository = NewsDetailsRepositoryImpl(dataSource)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataSource)
    }

    @Test
    fun getDetailsById() {
        whenever(dataSource.getDetailsById(newsId)).thenReturn(detailsResponse)

        assertSame(detailsResponse, repository.getDetailsById(newsId))

        verify(dataSource).getDetailsById(newsId)
    }

}