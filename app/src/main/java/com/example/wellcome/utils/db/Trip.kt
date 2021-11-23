package com.example.wellcome.utils.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trip(
    @Embedded val slotDate: SlotDate,
    @Embedded val tripCity : TripCity,
    @Embedded val hostConfiguration: HostConfiguration,
    val phone: String,
    val adress: String,
    val travelers: Int,
    val hasDogs : Boolean,
    val hasBabies:Boolean,
    val hasChilds:Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}