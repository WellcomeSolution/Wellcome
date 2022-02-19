package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class HostReservationDto(
    @SerializedName("Email")
    var email: String? = null,
    @SerializedName("Message")
    var message: String? = null,
    @SerializedName("Phone")
    var phone: String? = null,
    @SerializedName("HostUuid")
    var hostUuid: String? = null,
    @SerializedName("Uuid")
    var uuid: String? = null,
)