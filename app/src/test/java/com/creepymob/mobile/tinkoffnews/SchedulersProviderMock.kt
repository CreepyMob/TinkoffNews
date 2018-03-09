package com.creepymob.mobile.tinkoffnews

import com.creepymob.mobile.tinkoffnews.domain.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 13:27
 *
 */
class SchedulersProviderMock(private val scheduler: Scheduler = Schedulers.trampoline()) : SchedulersProvider {

    override fun single() = scheduler

    override fun computation() = scheduler

    override fun io() = scheduler

    override fun trampoline() = scheduler

    override fun newThread() = scheduler

    override fun main() = scheduler
}