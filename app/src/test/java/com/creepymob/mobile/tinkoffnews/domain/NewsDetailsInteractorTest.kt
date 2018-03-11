package com.creepymob.mobile.tinkoffnews.domain

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
 * Time: 0:20
 */
@RunWith(MockitoJUnitRunner::class)
class NewsDetailsInteractorTest {

    private lateinit var interactor: NewsDetailsInteractor
    @Mock private lateinit var repository: NewsDetailsRepository
    @Mock private lateinit var detailsResponse: Single<NewsEntryDetails>
    private val newsId = 1000L

    @Before
    fun setUp() {
        interactor = NewsDetailsInteractor(newsId, repository)
        whenever(repository.getDetailsById(newsId)).thenReturn(detailsResponse)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getDetails() {
        assertSame(detailsResponse, interactor.getDetails())

        verify(repository).getDetailsById(newsId)
    }

}