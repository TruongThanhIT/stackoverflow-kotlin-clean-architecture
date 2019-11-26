package com.thanht.stackoverflow.data.responses

import com.google.gson.annotations.SerializedName

class ReputationListResponse {
    @SerializedName("items")
    val userList: List<ReputationResponse> = emptyList()
}