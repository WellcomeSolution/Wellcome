package com.example.wellcome.utils.db

import android.provider.BaseColumns
import androidx.room.Entity
import com.example.wellcome.models.Equipments

@Entity
data class HostConfiguration(
    val rooms:Int,
    val beds: Int,
    val bathrooms : Int,
    val equipments : List<Equipments>)
