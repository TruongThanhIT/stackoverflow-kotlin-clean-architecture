package com.thanht.stackoverflow.presentation.di.component

import android.content.Context
import dagger.Component
import com.thanht.stackoverflow.data.net.ApiConnection
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import com.thanht.stackoverflow.presentation.di.module.NetworkModule
import com.thanht.stackoverflow.presentation.di.module.SchedulerModule
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SchedulerModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context
    fun apiConnection(): ApiConnection
    fun workScheduler(): WorkScheduler
    fun resultScheduler(): ResultScheduler
}
