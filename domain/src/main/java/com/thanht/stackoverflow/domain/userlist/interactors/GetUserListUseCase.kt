package com.thanht.stackoverflow.domain.userlist.interactors

import com.thanht.stackoverflow.domain.base.UseCase
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.userlist.UserListRepository
import com.thanht.stackoverflow.domain.userlist.requests.GetUserListParams
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import io.reactivex.Observable
import javax.inject.Inject

class GetUserListUseCase @Inject internal constructor(workScheduler: WorkScheduler,
                                                      resultScheduler: ResultScheduler,
                                                      private val userListRepository: UserListRepository)
    : UseCase<List<UserInfo>, GetUserListParams>(workScheduler, resultScheduler) {

    override fun buildUseCaseObservable(params: GetUserListParams?): Observable<List<UserInfo>> {
        return userListRepository.getUserList(params)
    }
}