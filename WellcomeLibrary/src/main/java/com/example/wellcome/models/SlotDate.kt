package com.example.wellcome.models

import java.time.LocalDate
import java.util.*

class SlotDate(val startDate: LocalDate, val endDate: LocalDate){
    override fun equals(other: Any?) = (other is SlotDate)
            && startDate == other.startDate
            && endDate == other.endDate
}