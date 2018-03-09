package com.creepymob.mobile.tinkoffnews.app

import android.app.Application
import com.creepymob.mobile.tinkoffnews.di.AppComponent
import com.creepymob.mobile.tinkoffnews.di.DaggerAppComponent

/**
 * User: andrey
 * Date: 08.03.2018
 * Time: 22:52
 *
 */
class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .context(this)
                .build()
    }
}