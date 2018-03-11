package com.creepymob.mobile.tinkoffnews.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.creepymob.mobile.tinkoffnews.entity.NewsEntry

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:36
 *
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView : MvpView {

    fun showNews(news: List<NewsEntry>)

    fun showProgress()

    fun hideProgress()

    fun showError(exception: Throwable)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorMessage(exception: Throwable)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNewsDetails(newsEntryId: Long)

    fun showRefreshProgress()

    fun hideRefreshProgress()
}