package com.thanht.stackoverflow.domain.userlist.interactors

import com.thanht.stackoverflow.domain.base.UseCase
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.userlist.UserListRepository
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import io.reactivex.Observable
import javax.inject.Inject

class SaveUserBookMarkedUseCase @Inject internal constructor(workScheduler: WorkScheduler,
                                                             resultScheduler: ResultScheduler,
                                                             private val mUserListRepository: UserListRepository)
    : UseCase<Any, UserInfo>(workScheduler, resultScheduler) {
    override fun buildUseCaseObservable(params: UserInfo?): Observable<Any> {
        return mUserListRepository.storeUserBookMarked(params!!).toObservable()
    }
}