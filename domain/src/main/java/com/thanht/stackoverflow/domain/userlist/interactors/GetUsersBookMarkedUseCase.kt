package com.thanht.stackoverflow.domain.userlist.interactors

import com.thanht.stackoverflow.domain.base.UseCase
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.userlist.UserListRepository
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import io.reactivex.Observable
import javax.inject.Inject

class GetUsersBookMarkedUseCase @Inject internal constructor(workScheduler: WorkScheduler,
                                                             resultScheduler: ResultScheduler,
                                                             private val mUserListRepository: UserListRepository)
    : UseCase<List<UserInfo>, Any>(workScheduler, resultScheduler) {

    override fun buildUseCaseObservable(params: Any?): Observable<List<UserInfo>> {
        return mUserListRepository.usersBookMarked.toObservable()
    }
}