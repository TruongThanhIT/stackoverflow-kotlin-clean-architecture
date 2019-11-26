package com.thanht.stackoverflow.data.cache

import com.thanht.stackoverflow.data.entity.UserInfoEntity
import com.thanht.stackoverflow.domain.model.UserInfo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserCached {
    val usersBookMarked: Single<List<UserInfoEntity>>
    fun saveUserBookMarked(userInfo : UserInfo): Completable
    fun removeUserBookMarked(userInfo: UserInfo): Completable
}
