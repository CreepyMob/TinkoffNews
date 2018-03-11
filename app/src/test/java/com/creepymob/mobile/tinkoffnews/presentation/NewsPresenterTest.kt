package com.creepymob.mobile.tinkoffnews.presentation

import com.creepymob.mobile.tinkoffnews.SchedulersProviderMock
import com.creepymob.mobile.tinkoffnews.domain.NewsInteractor
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
 * Time: 0:08
 */
@RunWith(MockitoJUnitRunner::class)
class NewsPresenterTest {

    @Mock private lateinit var viewState: `NewsView$$State`
    @Mock private lateinit var view: NewsView
    @Mock private lateinit var interactor: NewsInteractor
    private lateinit var schedulerProvider: SchedulersProvider

    @Mock private lateinit var newsList: List<NewsEntry>
    private lateinit var presenter: NewsPresenter

    @Before
    fun setUp() {
        schedulerProvider = spy(SchedulersProviderMock())
        presenter = NewsPresenter(interactor, schedulerProvider)
        presenter.setViewState(viewState)
    }


    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewState)
        verifyNoMoreInteractions(interactor)
        verifyNoMoreInteractions(schedulerProvider)
    }

    @Test
    fun onFirstViewAttachSuccess() {
        whenever(interactor.loadNews()).thenReturn(Single.just(newsList))

        presenter.attachView(view)

        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(viewState).attachView(view)
            verify(interactor).loadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showNews(newsList)
        }
    }

    @Test
    fun onFirstViewAttachError() {
        val exception = mock<RuntimeException>()
        whenever(interactor.loadNews()).thenReturn(Single.error(exception))

        presenter.attachView(view)

        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(viewState).attachView(view)
            verify(interactor).loadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showError(exception)
        }
    }

    @Test
    fun onReloadClickSuccess() {
        whenever(interactor.loadNews()).thenReturn(Single.just(newsList))

        presenter.onReloadClick()

        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(interactor).loadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showNews(newsList)
        }
    }


    @Test
    fun onReloadClickError() {
        val exception = mock<RuntimeException>()
        whenever(interactor.loadNews()).thenReturn(Single.error(exception))

        presenter.onReloadClick()
        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(interactor).loadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showError(exception)
        }
    }

    @Test
    fun onRefreshClickSuccess() {
        whenever(interactor.reloadNews()).thenReturn(Single.just(newsList))

        presenter.onRefreshClick()

        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(interactor).reloadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showRefreshProgress()
            verify(viewState).hideRefreshProgress()
            verify(viewState).showNews(newsList)
        }
    }

    @Test
    fun onRefreshClickError() {
        val exception = mock<RuntimeException>()
        whenever(interactor.reloadNews()).thenReturn(Single.error(exception))

        presenter.onRefreshClick()
        inOrder(viewState, interactor, schedulerProvider).apply {
            verify(interactor).reloadNews()
            verify(schedulerProvider).io()
            verify(schedulerProvider).main()
            verify(viewState).showRefreshProgress()
            verify(viewState).hideRefreshProgress()
            verify(viewState).showErrorMessage(exception)
        }
    }

    @Test
    fun onNewsEntryClick() {
        val newsEntryId = 100L
        val newsEntry = mock<NewsEntry>()
        whenever(newsEntry.id).thenReturn(newsEntryId)

        presenter.onNewsEntryClick(newsEntry)
        inOrder(viewState, interactor).apply {
            verify(viewState).showNewsDetails(newsEntryId)
        }
    }
}