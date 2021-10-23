package com.example.wellcome.models

import java.util.*

class SlotDate(val startDate: Date, val endDate: Date){
    override fun equals(other: Any?) = (other is SlotDate)
            && startDate == other.startDate
            && endDate == other.endDate
}