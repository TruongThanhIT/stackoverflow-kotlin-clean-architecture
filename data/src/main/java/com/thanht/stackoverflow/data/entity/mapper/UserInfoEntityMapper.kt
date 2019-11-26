package com.thanht.stackoverflow.data.entity.mapper

import com.thanht.stackoverflow.data.entity.UserInfoEntity
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.model.mapper.Mapper

class UserInfoEntityMapper : Mapper<UserInfoEntity, UserInfo>() {
    override fun map(value: UserInfoEntity?): UserInfo? {
        return if (value != null) {
            UserInfo(value.userId, value.displayName, value.profileImage, value.reputation,
                    value.location, value.lastAccessDate)
        } else null
    }

    override fun revertMap(value: UserInfo?): UserInfoEntity? {
        return if (value != null) {
            UserInfoEntity(value.userId, value.displayName, value.profileImage, value.reputation,
                    value.location, value.lastAccessDate)
        } else null
    }
}