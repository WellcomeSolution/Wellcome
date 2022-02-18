package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class HostReservationRequest(
    @SerializedName("Email")
    var email: String,
    @SerializedName("Message")
    var message: String,
    @SerializedName("Phone")
    var phone: String,
    @SerializedName("HostUuid")
    var hostUuid: String,
)