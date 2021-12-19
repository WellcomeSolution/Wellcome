package com.example.services

import kotlinx.serialization.Serializable

@Serializable
class HostConfiguration(
    val rooms:Int,
    val beds: Int,
    val bathrooms : Int,
    //val equipments : List<Equipments>
    )