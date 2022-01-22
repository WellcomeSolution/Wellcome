package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class HostConfiguration(
    @SerializedName("Rooms")
    val rooms:Int,
    @SerializedName("Beds")
    val beds: Int,
    @SerializedName("Bathrooms")
    val bathrooms : Int,
)