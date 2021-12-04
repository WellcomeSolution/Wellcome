package com.example.wellcome.utils.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class AssistanceRequest(
    val startDate: String,
    val endDate: String,
    val message:String,
    val phone: String,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)