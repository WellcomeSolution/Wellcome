package com.example.services

import kotlinx.serialization.Serializable

@Serializable
data class TripPattern(
    val requestEmail:String,
    val adults: Int,
    val babies: Int,
    val childs: Int,
    val longitude : Double,
    val latitude : Double)