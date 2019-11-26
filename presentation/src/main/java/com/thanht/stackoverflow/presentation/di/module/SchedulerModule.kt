package com.thanht.stackoverflow.presentation.di.module

import com.thanht.stackoverflow.data.scheduler.WorkSchedulerImpl
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import com.thanht.stackoverflow.presentation.scheduler.ResultSchedulerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SchedulerModule {
    @Binds
    @Singleton
    abstract fun provideWorkScheduler(workScheduler: WorkSchedulerImpl): WorkScheduler

    @Binds
    @Singleton
    abstract fun provideResultScheduler(resultScheduler: ResultSchedulerImpl): ResultScheduler
}
