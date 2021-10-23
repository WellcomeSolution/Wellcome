package com.example.wellcome.models.utils

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class DateUtils {
    companion object {
        fun asLocalDate(date:String) : LocalDate = LocalDate.parse("2018-12-12" )
        fun asDate(localDate: LocalDate): Date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        fun asDate(date:String) : Date = asDate(asLocalDate(date))
    }
}