package com.example.wellcome.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.services.Priority



@Entity
data class Assistance(
    val title: String,
    val description: String,
    @Embedded val address: Address,
    val phone: String,
    val tags: List<String>,
    val priority: Priority
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}