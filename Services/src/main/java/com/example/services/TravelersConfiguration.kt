package com.example.services

import kotlinx.serialization.Serializable

@Serializable
class TravelersConfiguration(
    val adults:Int,
    val babies:Int,
    val pets:Int,
    val childs:Int)