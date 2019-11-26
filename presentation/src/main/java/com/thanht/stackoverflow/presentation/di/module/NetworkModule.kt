package com.thanht.stackoverflow.presentation.di.module


import dagger.Module
import dagger.Provides
import com.thanht.stackoverflow.data.net.ApiConnection
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideApiConnection(): ApiConnection {
        return ApiConnection()
    }
}
