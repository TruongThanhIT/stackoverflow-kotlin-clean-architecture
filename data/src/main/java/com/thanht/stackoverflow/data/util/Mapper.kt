package com.thanht.stackoverflow.data.util

import com.thanht.stackoverflow.data.responses.ReputationResponse
import com.thanht.stackoverflow.data.responses.UserInfoResponse
import com.thanht.stackoverflow.domain.model.Reputation
import com.thanht.stackoverflow.domain.model.UserInfo

fun UserInfoResponse.toUserInfo(): UserInfo = UserInfo(
        userId = userId,
        displayName = displayName,
        profileImage = profileImage,
        reputation = reputation,
        location = location,
        lastAccessDate = lastAccessDate
)

fun List<UserInfoResponse>.toUserInfos(): List<UserInfo> =
        this.map { it.toUserInfo() }

fun ReputationResponse.toReputation(): Reputation = Reputation(
        reputationHistoryType = reputationHistoryType,
        reputationChanged = reputationChanged,
        postId = postId,
        createdDate = createdDate
)

fun List<ReputationResponse>.toReputationList(): List<Reputation> =
        this.map { it.toReputation() }