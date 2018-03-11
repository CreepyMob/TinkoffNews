package com.creepymob.mobile.tinkoffnews.di

import com.creepymob.mobile.tinkoffnews.presentation.NewsDetailsPresenter
import dagger.Subcomponent

/**
 * User: andrey
 * Date: 10.03.2018
 * Time: 0:39
 *
 */
@Subcomponent(modules = [NewsDetailsModule::class])
interface NewsDetailsComponent {

    fun getNewsDetailsPresenter(): NewsDetailsPresenter
}