package com.example.services

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class AccountDto(
    @SerializedName("Email")
    var email: String?,
    @SerializedName("Password")
    var password: String?,
    @SerializedName("Firstname")
    var firstName: String?,
    @SerializedName("Lastname")
    var lastName: String?,
    @SerializedName("Gender")
    var gender: String?,
    @SerializedName("BirthDate")
    var birthDate: String?,
    @SerializedName("Phone")
    var phone: String?
) {
    constructor(email:String, password: String)
            : this(email, password, null, null, null, null, null)

    constructor(email:String, password: String, firstName: String, lastName: String, birthDate: String, phone: String)
            : this(email, password, firstName, lastName,null, birthDate, phone)
}