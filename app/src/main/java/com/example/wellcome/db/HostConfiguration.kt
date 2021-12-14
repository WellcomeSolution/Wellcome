package com.example.wellcome.db

import androidx.room.Entity
import com.example.services.Equipments

@Entity
data class HostConfiguration(
    val rooms:Int,
    val beds: Int,
    val bathrooms : Int,
    val equipments : List<Equipments>)
