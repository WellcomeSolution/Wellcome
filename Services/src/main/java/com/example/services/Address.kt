package com.example.services

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val country: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double)
