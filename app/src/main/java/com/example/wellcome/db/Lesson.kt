package com.example.wellcome.db
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lesson(
    val title: String,
    val description: String,
    @Embedded val address: Address,
    val phone: String,
    val tags: List<String>,
    val sessionDuration: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}