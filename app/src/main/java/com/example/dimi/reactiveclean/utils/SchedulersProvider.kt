package com.example.dimi.reactiveclean.utils

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler
}