package com.thanht.stackoverflow.data.userlist

import com.thanht.stackoverflow.data.cache.UserCached
import com.thanht.stackoverflow.data.entity.mapper.UserInfoEntityMapper
import com.thanht.stackoverflow.data.util.toUserInfos
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.userlist.UserListRepository
import com.thanht.stackoverflow.domain.userlist.requests.GetUserListParams
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserListRepositoryImpl(private val service: UserListService, private val mUserCached: UserCached) : UserListRepository {
    override fun getUserList(requestParams: GetUserListParams?): Observable<List<UserInfo>> =
            if (requestParams != null) {
                service.getUsers(requestParams.page, requestParams.pageSize, requestParams.site)
                        .flatMap { return@flatMap Observable.just(it.userList.toUserInfos()) }
            } else {
                Observable.just(emptyList())
            }

    override fun removeUserBookMarked(userInfo: UserInfo): Completable =
            mUserCached.removeUserBookMarked(userInfo)

    override fun storeUserBookMarked(userInfo: UserInfo): Completable =
            mUserCached.saveUserBookMarked(userInfo)

    override val usersBookMarked: Single<List<UserInfo>>
        get() = mUserCached.usersBookMarked.map {
            UserInfoEntityMapper().map(it)
        }
}