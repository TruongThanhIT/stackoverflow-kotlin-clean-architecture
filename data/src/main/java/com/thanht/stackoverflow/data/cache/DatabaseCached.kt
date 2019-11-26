package com.thanht.stackoverflow.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thanht.stackoverflow.data.entity.UserInfoEntity
import javax.inject.Inject

@Database(entities = [UserInfoEntity::class], version = 1, exportSchema = false)
abstract class DatabaseCached @Inject constructor() : RoomDatabase() {
    abstract fun userDao(): UserInfoDao
}
