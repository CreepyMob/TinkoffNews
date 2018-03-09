package com.creepymob.mobile.tinkoffnews.domain

import io.reactivex.Scheduler

/**
 * User: andrey
 * Date: 09.03.2018
 * Time: 13:11
 *
 */
interface SchedulersProvider {

    fun single(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

    fun trampoline(): Scheduler

    fun newThread(): Scheduler

    fun main(): Scheduler
}