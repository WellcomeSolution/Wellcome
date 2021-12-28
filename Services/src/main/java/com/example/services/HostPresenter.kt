package com.example.services

import android.graphics.Bitmap
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
class HostPresenter(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Longitude")
    val longitude: Double,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("PictureUrl")
    val pictureUrl: String
)