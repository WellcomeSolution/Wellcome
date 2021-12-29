package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Hoster(
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("Gender")
    val gender: String,
    @SerializedName("Age")
    val age: Int,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Profession")
    val profession: String,
    @SerializedName("Language")
    val language: String
)