package com.thanht.stackoverflow.data.responses

import com.google.gson.annotations.SerializedName

class ReputationResponse {
    @SerializedName("reputation_history_type")
    val reputationHistoryType: String? = null

    @SerializedName("reputation_change")
    val reputationChanged: Int = 0

    @SerializedName("post_id")
    val postId: Int = 0

    @SerializedName("creation_date")
    val createdDate: Long = 0L
}