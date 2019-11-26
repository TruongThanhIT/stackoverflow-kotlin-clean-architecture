package com.thanht.stackoverflow.domain.scheduler

import io.reactivex.Scheduler

interface ResultScheduler {
    val scheduler: Scheduler
}
