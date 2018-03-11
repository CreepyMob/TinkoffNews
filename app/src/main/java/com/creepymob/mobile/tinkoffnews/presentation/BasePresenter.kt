package com.creepymob.mobile.tinkoffnews.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 23:43
 *
 */
abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}