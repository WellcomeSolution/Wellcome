package com.example.wellcome.utils

import android.content.Context
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class CitiesHelper {
    companion object {
        fun getCities(context:Context) : Collection<City> {
            val stream = context.assets.getStream("cities.json")
            return  Json { ignoreUnknownKeys = true }.decodeFromStream(stream)
        }
    }
}

@Serializable
data class City (
    val country: String,
    val name: String,
    val longitude:Double,
    val latitude:Double
)