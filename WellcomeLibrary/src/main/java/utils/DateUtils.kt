package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {
        fun asLocalDate(date:String) : LocalDate {
            val df = DateTimeFormatter.ofPattern("d/MM/yyyy")
            return LocalDate.parse(date, df)
        }
        fun asString(localDate: LocalDate) : String = localDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
    }
}