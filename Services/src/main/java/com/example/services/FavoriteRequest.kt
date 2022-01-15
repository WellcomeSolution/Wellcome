package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class FavoriteRequest(
    @SerializedName("Email")
    val email: String,
    @SerializedName("HostUuid")
    val hostUuid: String,
)