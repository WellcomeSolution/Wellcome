package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerializedName("City")
    val city: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("PostalCode")
    val postalCode: String,
    @SerializedName("Latitude")
    val latitude: Double?,
    @SerializedName("Longitude")
    val longitude: Double?)
