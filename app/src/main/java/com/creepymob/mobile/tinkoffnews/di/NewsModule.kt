package com.creepymob.mobile.tinkoffnews.di

import com.creepymob.mobile.tinkoffnews.data.database.NewsDao
import com.creepymob.mobile.tinkoffnews.data.network.TinkoffApi
import com.creepymob.mobile.tinkoffnews.data.news.NewsDataSourceLocale
import com.creepymob.mobile.tinkoffnews.data.news.NewsDataSourceRemote
import com.creepymob.mobile.tinkoffnews.data.news.NewsRepositoryImpl
import com.creepymob.mobile.tinkoffnews.domain.DatableSorter
import com.creepymob.mobile.tinkoffnews.domain.NewsInteractor
import com.creepymob.mobile.tinkoffnews.domain.NewsRepository
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import com.creepymob.mobile.tinkoffnews.presentation.NewsPresenter
import dagger.Module
import dagger.Provides

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 18:00
 *
 */
@Module
class NewsModule {

    @Provides
    fun provideNewsPresenter(newsInteractor: NewsInteractor, schedulersProvider: SchedulersProvider): NewsPresenter = NewsPresenter(newsInteractor, schedulersProvider)

    @Provides
    fun provideNewsInteractor(newsRepository: NewsRepository, datableSorter: DatableSorter): NewsInteractor = NewsInteractor(newsRepository, datableSorter)

    @Provides
    fun provideNewsRepository(newsDataSourceLocale: NewsDataSourceLocale, newsDataSourceRemote: NewsDataSourceRemote): NewsRepository =
            NewsRepositoryImpl(newsDataSourceLocale, newsDataSourceRemote)

    @Provides
    fun provideNewsDataSourceLocale(newsDao: NewsDao): NewsDataSourceLocale =
            NewsDataSourceLocale(newsDao)

    @Provides
    fun provideNewsDataSourceRemote(tinkoffApi: TinkoffApi): NewsDataSourceRemote = NewsDataSourceRemote(tinkoffApi)

}