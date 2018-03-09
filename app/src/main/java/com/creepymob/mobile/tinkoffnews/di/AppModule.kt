package com.creepymob.mobile.tinkoffnews.di

import com.creepymob.mobile.tinkoffnews.app.SchedulersProviderImpl
import com.creepymob.mobile.tinkoffnews.domain.DatableSorter
import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 18:39
 *
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSchedulersProvider(): SchedulersProvider = SchedulersProviderImpl()

    @Singleton
    @Provides
    fun provideDatableSorter(): DatableSorter = DatableSorter()
}