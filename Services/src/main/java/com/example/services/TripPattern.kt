package com.example.services

import kotlinx.serialization.Serializable

@Serializable
data class TripPattern(val adults: Int,
                  val babies: Int,
                  val pets: Int,
                  val childs: Int,
                  val city : String,
                  val country : String,
                  val postalCode : String,
                  val longitude : Double,
                  val latitude : Double)