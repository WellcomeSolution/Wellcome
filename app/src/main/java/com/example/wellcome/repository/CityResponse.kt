package com.example.wellcome.repository

import kotlinx.serialization.Serializable


@Serializable
data class CityResponse (
    val data: ArrayList<City>,
    val metadata: Metadata
)

@Serializable
data class City (
    val id: Long,

    val type: String,
    val city: String,
    val name: String,
    val country: String,
    val countryCode: String,
    val region: String,
    val regionCode: String,
    val latitude: Double,
    val longitude: Double,
    val population: Long
)

@Serializable
data class Metadata (
    val currentOffset: Long,
    val totalCount: Long
)