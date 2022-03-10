package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class HostReservationPresenterDto(
    @SerializedName("StartDate")
    var startDate: String? = null,
    @SerializedName("EndDate")
    var endDate: String? = null,
    @SerializedName("FirstName")
    var firstName: String? = null,
    @SerializedName("Description")
    var description: String? = null,
    @SerializedName("HostTitle")
    var hostTitle: String? = null,
    @SerializedName("ApplicantPhoto")
    var applicantPhoto: String? = null,
    @SerializedName("Uuid")
    var uuid: String? = null
)