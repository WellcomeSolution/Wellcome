package com.example.wellcome.repository

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class City (
    @SerializedName("place_id"     ) var placeId     : Int?         = null,
    @SerializedName("licence"      ) var licence     : String?      = null,
    @SerializedName("osm_type"     ) var osmType     : String?      = null,
    @SerializedName("osm_id"       ) var osmId       : Int?         = null,
    @SerializedName("boundingbox"  ) var boundingbox : List<String> = arrayListOf(),
    @SerializedName("lat"          ) var lat         : String?      = null,
    @SerializedName("lon"          ) var lon         : String?      = null,
    @SerializedName("display_name" ) var displayName : String?      = null,
    @SerializedName("type"         ) var type        : String?      = null,
    @SerializedName("importance"   ) var importance  : Double?      = null,
    @SerializedName("icon"         ) var icon        : String?      = null,
    @SerializedName("address"      ) var address     : Address?     = Address()
)

@Serializable
data class Address (
    @SerializedName("city"         ) var city         : String? = null,
    @SerializedName("municipality" ) var municipality : String? = null,
    @SerializedName("county"       ) var county       : String? = null,
    @SerializedName("state"        ) var state        : String? = null,
    @SerializedName("region"       ) var region       : String? = null,
    @SerializedName("postcode"     ) var postcode     : String? = null,
    @SerializedName("country"      ) var country      : String? = null,
    @SerializedName("country_code" ) var countryCode  : String? = null
)