package com.creepymob.mobile.tinkoffnews.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.creepymob.mobile.tinkoffnews.entity.NewsEntryDetails

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 23:41
 *
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsDetailsView : MvpView {

    fun showDetails(newsEntryDetails: NewsEntryDetails)

    fun showProgress()

    fun hideProgress()

    fun showError(exception: Throwable)


}