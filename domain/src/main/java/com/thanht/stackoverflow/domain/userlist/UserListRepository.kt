package com.thanht.stackoverflow.domain.userlist

import com.thanht.stackoverflow.domain.userlist.requests.GetUserListParams
import io.reactivex.Observable
import com.thanht.stackoverflow.domain.model.UserInfo
import io.reactivex.Completable
import io.reactivex.Single

interface UserListRepository {
    fun getUserList(requestParams: GetUserListParams?): Observable<List<UserInfo>>
    fun storeUserBookMarked(userInfo: UserInfo): Completable
    fun removeUserBookMarked(userInfo: UserInfo): Completable
    val usersBookMarked: Single<List<UserInfo>>
}