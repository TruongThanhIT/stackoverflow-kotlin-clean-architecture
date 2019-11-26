package com.thanht.stackoverflow.presentation.model

class UserInfoModel(
        val userId: Int = 0,
        val displayName: String? = null,
        val profileImage: String? = null,
        val reputation: Int = 0,
        val location: String? = null,
        val lastAccessDate: Long = 0L,
        var isBookMarked: Boolean = false
)