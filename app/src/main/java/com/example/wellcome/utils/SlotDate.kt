package com.example.wellcome.utils

import android.provider.BaseColumns
import androidx.room.Entity
import java.time.LocalDate
import java.util.*

@Entity
data class SlotDate(
    val startDate: LocalDate,
    val endDate: LocalDate)