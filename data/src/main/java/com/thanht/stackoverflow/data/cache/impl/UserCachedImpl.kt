package com.thanht.stackoverflow.data.cache.impl

import android.content.Context
import androidx.room.Room
import com.thanht.stackoverflow.data.cache.DatabaseCached
import com.thanht.stackoverflow.data.cache.UserCached
import com.thanht.stackoverflow.data.entity.UserInfoEntity
import com.thanht.stackoverflow.data.entity.mapper.UserInfoEntityMapper
import com.thanht.stackoverflow.domain.model.UserInfo
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserCachedImpl @Inject
internal constructor(context: Context) : UserCached {
    private val database = Room.databaseBuilder(
            context.applicationContext,
            DatabaseCached::class.java,
            "stack_over_flow.db"
    ).build()
    private val usersDao = database.userDao()

    override val usersBookMarked: Single<List<UserInfoEntity>>
        get() = usersDao.getAll()

    override fun saveUserBookMarked(userInfo: UserInfo): Completable =
            usersDao.insert(UserInfoEntityMapper().revertMap(userInfo)!!)

    override fun removeUserBookMarked(userInfo: UserInfo): Completable =
            usersDao.delete(UserInfoEntityMapper().revertMap(userInfo)!!)
}
