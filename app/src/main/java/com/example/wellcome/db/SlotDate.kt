package com.example.wellcome.db

import androidx.room.Entity
import java.time.LocalDate

@Entity
data class SlotDate(
    val startDate: LocalDate,
    val endDate: LocalDate)