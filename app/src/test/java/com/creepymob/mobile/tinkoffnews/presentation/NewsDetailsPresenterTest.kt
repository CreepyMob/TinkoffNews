package com.creepymob.mobile.tinkoffnews.presentation

import com.creepymob.mobile.tinkoffnews.SchedulersProviderMock
import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsInteractor
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.spy
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
 * Time: 23:56
 */
@RunWith(MockitoJUnitRunner::class)
class NewsDetailsPresenterTest {

    private lateinit var presenter: NewsDetailsPresenter
    @Mock private lateinit var interactor: NewsDetailsInteractor
    @Mock private lateinit var view: NewsDetailsView
    @Mock private lateinit var viewState: `NewsDetailsView$$State`
    @Mock private lateinit var newsEntryDetails: NewsEntryDetails
    private lateinit var schedulers: SchedulersProvider

    @Before
    fun setUp() {
        schedulers = spy(SchedulersProviderMock())
        presenter = NewsDetailsPresenter(interactor, schedulers)
        presenter.setViewState(viewState)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewState)
        verifyNoMoreInteractions(schedulers)
        verifyNoMoreInteractions(interactor)
    }

    @Test
    fun onFirstViewAttachSuccess() {
        whenever(interactor.getDetails()).thenReturn(Single.just(newsEntryDetails))
        presenter.attachView(view)

        inOrder(viewState, interactor, schedulers).apply {
            verify(viewState).attachView(view)
            verify(interactor).getDetails()
            verify(schedulers).io()
            verify(schedulers).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showDetails(newsEntryDetails)
        }
    }

    @Test
    fun onFirstViewAttachError() {
        val exception = RuntimeException()
        whenever(interactor.getDetails()).thenReturn(Single.error(exception))
        presenter.attachView(view)

        inOrder(viewState, interactor, schedulers).apply {
            verify(viewState).attachView(view)
            verify(interactor).getDetails()
            verify(schedulers).io()
            verify(schedulers).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showError(exception)
        }
    }

    @Test
    fun onReloadSuccess() {
        whenever(interactor.getDetails()).thenReturn(Single.just(newsEntryDetails))
        presenter.onReload()

        inOrder(viewState, interactor, schedulers).apply {

            verify(interactor).getDetails()
            verify(schedulers).io()
            verify(schedulers).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showDetails(newsEntryDetails)
        }
    }

    @Test
    fun onReloadError() {
        val exception = RuntimeException()
        whenever(interactor.getDetails()).thenReturn(Single.error(exception))
        presenter.onReload()

        inOrder(viewState, interactor, schedulers).apply {

            verify(interactor).getDetails()
            verify(schedulers).io()
            verify(schedulers).main()
            verify(viewState).showProgress()
            verify(viewState).hideProgress()
            verify(viewState).showError(exception)
        }
    }

}