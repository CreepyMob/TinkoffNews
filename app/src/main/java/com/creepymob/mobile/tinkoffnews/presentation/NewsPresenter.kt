package com.creepymob.mobile.tinkoffnews.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.creepymob.mobile.tinkoffnews.domain.NewsInteractor
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 23:29
 *
 */
@InjectViewState
class NewsPresenter(private val interactor: NewsInteractor, private val schedulers: SchedulersProvider) : MvpPresenter<NewsView>() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        System.out.println("NewsPresenter onFirstViewAttach")
        load()
    }

    private fun load() {
        interactor.loadNews()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnSubscribe {
                    viewState.showProgress()
                }
                .doOnEvent { _, _ ->
                    viewState.hideProgress()
                }
                .subscribe({
                    viewState.showNews(it)
                }, {
                    viewState.showError(it)
                }).addTo(disposable)
    }

    fun onReloadClick() {
        load()
    }

    fun onRefreshClick() {
        interactor.reloadNews()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnSubscribe { viewState.showRefreshProgress() }
                .doOnEvent { _, _ -> viewState.hideRefreshProgress() }
                .subscribe({
                    viewState.showNews(it)
                }, {
                    viewState.showErrorMessage(it)
                }).addTo(disposable)
    }

    fun onNewsEntryClick(newsEntry: NewsEntry) {
        viewState.showNewsDetails(newsEntry.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}