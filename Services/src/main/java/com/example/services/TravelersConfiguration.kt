package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class TravelersConfiguration(
    @SerializedName("Adults")
    val adults:Int,
    @SerializedName("Babies")
    val babies:Int,
    @SerializedName("Pets")
    val pets:Int,
    @SerializedName("Childs")
    val childs:Int)