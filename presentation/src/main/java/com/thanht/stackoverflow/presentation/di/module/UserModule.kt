package com.thanht.stackoverflow.presentation.di.module


import com.thanht.stackoverflow.data.cache.UserCached
import com.thanht.stackoverflow.data.net.ApiConnection
import com.thanht.stackoverflow.data.reputation.ReputationRepositoryImpl
import com.thanht.stackoverflow.data.reputation.ReputationService
import com.thanht.stackoverflow.data.userlist.UserListRepositoryImpl
import com.thanht.stackoverflow.data.userlist.UserListService
import com.thanht.stackoverflow.data.util.provideService
import com.thanht.stackoverflow.domain.reputation.ReputationRepository
import com.thanht.stackoverflow.domain.userlist.UserListRepository
import com.thanht.stackoverflow.presentation.di.scope.UserScope
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    @UserScope
    internal fun provideUserListService(apiConnection: ApiConnection): UserListService {
        return apiConnection.provideService()
    }

    @Provides
    @UserScope
    internal fun provideUserListRepository(userListService: UserListService,
                                           userCached: UserCached): UserListRepository {
        return UserListRepositoryImpl(userListService, userCached)
    }

    @Provides
    @UserScope
    internal fun provideReputationService(apiConnection: ApiConnection): ReputationService {
        return apiConnection.provideService()
    }

    @Provides
    @UserScope
    internal fun provideReputationRepository(reputationService: ReputationService): ReputationRepository {
        return ReputationRepositoryImpl(reputationService)
    }
}
