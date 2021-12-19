package com.example.wellcome.repository

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class City (
    @SerializedName("boundingbox"  ) var boundingbox : List<String> = arrayListOf(),
    @SerializedName("lat"          ) var lat         : String?      = "",
    @SerializedName("lon"          ) var lon         : String?      = "",
    @SerializedName("display_name" ) var displayName : String?      = "",
    @SerializedName("address"      ) var address     : Address?     = Address()
)

@Serializable
data class Address (
    @SerializedName("city"         ) var city         : String? = "",
    @SerializedName("municipality" ) var municipality : String? = "",
    @SerializedName("county"       ) var county       : String? = "",
    @SerializedName("state"        ) var state        : String? = "",
    @SerializedName("region"       ) var region       : String? = "",
    @SerializedName("postcode"     ) var postcode     : String? = "",
    @SerializedName("country"      ) var country      : String? = "",
    @SerializedName("country_code" ) var countryCode  : String? = ""
)