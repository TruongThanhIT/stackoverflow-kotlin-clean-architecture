package com.thanht.stackoverflow.presentation.di.module


import com.thanht.stackoverflow.data.cache.UserCached
import com.thanht.stackoverflow.data.cache.impl.UserCachedImpl
import com.thanht.stackoverflow.presentation.di.scope.UserScope
import dagger.Binds
import dagger.Module

@Module
abstract class CachedModule {
    @Binds
    @UserScope
    abstract fun provideDataCached(dataCached: UserCachedImpl): UserCached
}
