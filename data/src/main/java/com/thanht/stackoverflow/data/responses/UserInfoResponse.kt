package com.thanht.stackoverflow.data.responses

import com.google.gson.annotations.SerializedName

class UserInfoResponse {
    @SerializedName("user_id")
    val userId: Int = 0

    @SerializedName("display_name")
    val displayName: String? = null

    @SerializedName("profile_image")
    val profileImage: String? = null

    @SerializedName("reputation")
    val reputation: Int = 0

    @SerializedName("location")
    val location: String? = null

    @SerializedName("last_access_date")
    val lastAccessDate: Long = 0L
}