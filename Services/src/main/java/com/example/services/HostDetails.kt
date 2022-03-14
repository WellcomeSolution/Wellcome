package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class HostDetails(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Travelers")
    val travelers: Int,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Rooms")
    val rooms: Int,
    @SerializedName("Bathrooms")
    val bathrooms: Int,
    @SerializedName("Address")
    val address:Address,
    @SerializedName("Hoster")
    val hoster: Hoster)


