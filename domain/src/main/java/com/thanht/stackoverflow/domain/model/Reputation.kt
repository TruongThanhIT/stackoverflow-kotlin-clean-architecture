package com.thanht.stackoverflow.domain.model

class Reputation(
        val reputationHistoryType: String?= null,
        val reputationChanged: Int = 0,
        val postId: Int = 0,
        val createdDate: Long = 0L
)