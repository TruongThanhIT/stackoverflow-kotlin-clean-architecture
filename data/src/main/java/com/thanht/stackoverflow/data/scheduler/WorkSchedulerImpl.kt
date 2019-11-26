package com.thanht.stackoverflow.data.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import javax.inject.Inject

class WorkSchedulerImpl @Inject constructor() : WorkScheduler {
    override val scheduler: Scheduler = Schedulers.computation()
}
