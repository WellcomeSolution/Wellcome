package utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {
        fun asLocalDate(date:String) : LocalDate {
            val df = DateTimeFormatter.ofPattern("d/MM/yyyy")
            return LocalDate.parse(date, df)
        }
        fun asLocalDate(date:Long) : LocalDate
            = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
        fun asString(localDate: LocalDate) : String = localDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
    }
}