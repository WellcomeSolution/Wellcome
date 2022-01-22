package com.example.services

import com.google.gson.annotations.SerializedName

class HostRequest(
    @SerializedName("Email")
    var email: String,
    @SerializedName("Title")
    var title: String,
    @SerializedName("Description")
    var description: String,
    @SerializedName("PictureName")
    var pictureName: String,
    @SerializedName("Address")
    var address: Address,
    @SerializedName("HostConfiguration")
    var hostConfiguration: HostConfiguration,
    @SerializedName("TravelersConfiguration")
    var travelersConfiguration: TravelersConfiguration,
) {
}