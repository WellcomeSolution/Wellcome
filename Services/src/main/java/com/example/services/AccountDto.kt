package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class AccountDto(
    @SerializedName("Email")
    var email: String,
    @SerializedName("Firstname")
    var firstName: String?,
    @SerializedName("Lastname")
    var lastName: String?,
    @SerializedName("Password")
    var password: String,
    @SerializedName("BirthDate")
    var birthDate: String?,
    @SerializedName("Phone")
    var phone: String?
)