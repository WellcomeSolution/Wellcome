package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class IncomingTripDto(
    @SerializedName("HostTitle")
    var hostTitle: String? = null,
    @SerializedName("HostPhotoUrl")
    var hostPhotoUrl: String? = null,
    @SerializedName("Address")
    var address: Address? = null
)