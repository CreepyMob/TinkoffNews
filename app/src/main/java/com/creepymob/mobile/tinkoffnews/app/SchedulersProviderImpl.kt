package com.creepymob.mobile.tinkoffnews.app

import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 13:11
 *
 */
class SchedulersProviderImpl : SchedulersProvider {

    override fun single() = Schedulers.single()

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()

    override fun trampoline() = Schedulers.trampoline()

    override fun newThread() = Schedulers.newThread()

    override fun main() = AndroidSchedulers.mainThread()
}