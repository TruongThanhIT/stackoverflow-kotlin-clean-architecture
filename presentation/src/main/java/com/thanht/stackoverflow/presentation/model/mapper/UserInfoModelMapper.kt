package com.thanht.stackoverflow.presentation.model.mapper

import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.domain.model.mapper.Mapper
import com.thanht.stackoverflow.presentation.model.UserInfoModel

class UserInfoModelMapper : Mapper<UserInfo, UserInfoModel>() {
    override fun map(value: UserInfo?): UserInfoModel? {
        return if (value != null) {
            UserInfoModel(value.userId, value.displayName, value.profileImage,
                    value.reputation, value.location, value.lastAccessDate)
        } else null
    }

    override fun revertMap(value: UserInfoModel?): UserInfo? {
        return if (value != null) {
            UserInfo(value.userId, value.displayName, value.profileImage,
                    value.reputation, value.location, value.lastAccessDate)
        } else null
    }
}