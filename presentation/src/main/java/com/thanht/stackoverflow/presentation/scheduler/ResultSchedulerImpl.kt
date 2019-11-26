package com.thanht.stackoverflow.presentation.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import javax.inject.Inject

class ResultSchedulerImpl @Inject constructor() : ResultScheduler {
    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}
