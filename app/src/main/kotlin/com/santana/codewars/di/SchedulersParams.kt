package com.santana.codewars.di

import io.reactivex.Scheduler

class SchedulersParams(
    val ioScheduler: Scheduler,
    val mainScheduler: Scheduler
)