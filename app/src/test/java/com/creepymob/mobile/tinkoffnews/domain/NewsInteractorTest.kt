package com.creepymob.mobile.tinkoffnews.domain

import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
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
 * Time: 14:19
 */
@RunWith(MockitoJUnitRunner::class)
class NewsInteractorTest {

    private lateinit var interactor: NewsInteractor
    @Mock private lateinit var repository: NewsRepository
    @Mock private lateinit var sorter: DatableSorter
    @Mock private lateinit var newsList: List<NewsEntry>
    @Mock private lateinit var sortedNewsList: List<NewsEntry>

    @Before
    fun setUp() {
        interactor = NewsInteractor(repository, sorter)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
        verifyNoMoreInteractions(sorter)
    }

    @Test
    fun loadNews() {
        whenever(repository.getNews()).thenReturn(Single.just(newsList))
        whenever(sorter.sort(newsList)).thenReturn(sortedNewsList)

        interactor.loadNews().test()
                .assertNoErrors()
                .assertValue(sortedNewsList)
                .assertComplete()

        inOrder(repository, sorter).apply {
            verify(repository).getNews()
            verify(sorter).sort(newsList)
        }
    }

    @Test
    fun reloadNews() {
        whenever(repository.clearCache()).thenReturn(Completable.complete())
        whenever(repository.getNews()).thenReturn(Single.just(newsList))
        whenever(sorter.sort(newsList)).thenReturn(sortedNewsList)

        interactor.reloadNews().test()
                .assertNoErrors()
                .assertValue(sortedNewsList)
                .assertComplete()

        inOrder(repository, sorter).apply {
            verify(repository).clearCache()
            verify(repository).getNews()
            verify(sorter).sort(newsList)
        }
    }

}