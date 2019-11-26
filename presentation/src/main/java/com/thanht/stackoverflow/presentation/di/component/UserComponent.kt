package com.thanht.stackoverflow.presentation.di.component


import dagger.Component
import com.thanht.stackoverflow.presentation.di.module.CachedModule
import com.thanht.stackoverflow.presentation.di.module.UserModule
import com.thanht.stackoverflow.presentation.di.scope.UserScope
import com.thanht.stackoverflow.presentation.ui.userlist.UserListPresenter
import com.thanht.stackoverflow.presentation.ui.reputation.ReputationHistoryPresenter

@UserScope
@Component(dependencies = [ApplicationComponent::class], modules = [UserModule::class, CachedModule::class])
interface UserComponent {
    fun inject(presenter: UserListPresenter?)
    fun inject(presenter: ReputationHistoryPresenter?)
}
