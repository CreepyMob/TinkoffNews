package com.creepymob.mobile.tinkoffnews.presentation

import com.arellomobile.mvp.InjectViewState
import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsInteractor
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import io.reactivex.rxkotlin.addTo

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 23:42
 *
 */
@InjectViewState
class NewsDetailsPresenter(private val newsDetailsInteractor: NewsDetailsInteractor, private val schedulersProvider: SchedulersProvider) : BasePresenter<NewsDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        load()
    }

    private fun load() {

        newsDetailsInteractor.getDetails()
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.main())
                .doOnSubscribe {
                    viewState.showProgress()
                }
                .doOnEvent { _, _ ->
                    viewState.hideProgress()
                }
                .subscribe({
                    viewState.showDetails(it)
                }, {
                    viewState.showError(it)
                })
                .addTo(disposable)
    }

    fun onReload() {
        load()
    }
}