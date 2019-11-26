package com.thanht.stackoverflow.data.cache

import androidx.room.*
import com.thanht.stackoverflow.data.entity.UserInfoEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM user_list")
    fun getAll(): Single<List<UserInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfoEntity: UserInfoEntity): Completable

    @Delete
    fun delete(userInfoEntity: UserInfoEntity): Completable
}