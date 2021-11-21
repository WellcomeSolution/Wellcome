package com.example.wellcome.utils.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.wellcome.models.Priority


@Entity
data class Assistance(
    val isFavorite:Boolean,
    val title: String,
    val description: String,
    @Embedded val address: Address,
    val phone: String,
    val tags: List<String>,
    val isAvailable: Boolean,
    val priority: Priority) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}