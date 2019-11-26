package com.thanht.stackoverflow.data.responses

import com.google.gson.annotations.SerializedName
import com.thanht.stackoverflow.data.responses.UserInfoResponse

class UserListResponse {
    @SerializedName("items")
    val userList: List<UserInfoResponse> = emptyList()
}