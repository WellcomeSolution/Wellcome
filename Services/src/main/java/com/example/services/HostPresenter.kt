package com.example.services

import android.graphics.Bitmap
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.Json

@Serializable
class HostPresenter(
    @SerializedName("IsFavorite")
    var isFavorite: Boolean,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Uuid")
    val uuid: String,
    @SerializedName("FirstName")
    val firstName: String? = null,
    @SerializedName("LastName")
    val lastName: String? = null,
    @SerializedName("City")
    val city: String? = null,
    @SerializedName("Country")
    val country: String? = null,
    @SerializedName("Longitude")
    val longitude: Double? = null,
    @SerializedName("Latitude")
    val latitude: Double? = null,
    @SerializedName("PictureUrl")
    val pictureUrl: String
)