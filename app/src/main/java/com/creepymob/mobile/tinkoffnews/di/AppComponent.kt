package com.creepymob.mobile.tinkoffnews.di

import android.content.Context
import com.creepymob.mobile.tinkoffnews.presentation.NewsPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 23:09
 *
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, NewsModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

    fun getNewsPresenter(): NewsPresenter
}