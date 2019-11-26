package com.thanht.stackoverflow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_list")
data class UserInfoEntity(
        @PrimaryKey @ColumnInfo(name = "id") val userId: Int,
        @ColumnInfo(name = "name") val displayName: String?,
        @ColumnInfo(name = "image") val profileImage: String?,
        @ColumnInfo(name = "reputation") val reputation: Int = 0,
        @ColumnInfo(name = "location") val location: String?,
        @ColumnInfo(name = "last_access_date") val lastAccessDate: Long = 0L
)
