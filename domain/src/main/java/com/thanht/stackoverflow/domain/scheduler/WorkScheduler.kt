package com.thanht.stackoverflow.domain.scheduler

import io.reactivex.Scheduler

interface WorkScheduler {
    val scheduler: Scheduler
}
