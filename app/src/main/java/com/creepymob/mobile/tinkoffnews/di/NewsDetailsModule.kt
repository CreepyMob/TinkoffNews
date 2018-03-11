package com.creepymob.mobile.tinkoffnews.di

import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.creepymob.mobile.tinkoffnews.data.news.NewsDetailsDataSourceRemote
import com.creepymob.mobile.tinkoffnews.data.news.NewsDetailsRepositoryImpl
import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsInteractor
import com.creepymob.mobile.tinkoffnews.domain.NewsDetailsRepository
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.presentation.NewsDetailsPresenter
import dagger.Module
import dagger.Provides

/**
 * User: andrey
 * Date: 10.03.2018
 * Time: 0:39
 *
 */
@Module
class NewsDetailsModule(private val newsId: Long) {

    @Provides
    fun provideNewsDetailsPresenter(newsDetailsInteractor: NewsDetailsInteractor, schedulersProvider: SchedulersProvider): NewsDetailsPresenter =
            NewsDetailsPresenter(newsDetailsInteractor, schedulersProvider)

    @Provides
    fun provideNewsDetailsInteractor(newsDetailsRepository: NewsDetailsRepository): NewsDetailsInteractor =
            NewsDetailsInteractor(newsId, newsDetailsRepository)

    @Provides
    fun provideNewsDetailsRepository(newsDetailsDataSourceRemote: NewsDetailsDataSourceRemote): NewsDetailsRepository =
            NewsDetailsRepositoryImpl(newsDetailsDataSourceRemote)

    @Provides
    fun provideNewsDetailsDataSourceRemote(tinkoffApi: TinkoffApi): NewsDetailsDataSourceRemote =
            NewsDetailsDataSourceRemote(tinkoffApi)
}