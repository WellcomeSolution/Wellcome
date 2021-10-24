package com.example.wellcome

import utils.DateUtils
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTests {
    @Test
    fun toLocalDateTest(){
        val localDate = DateUtils.asLocalDate("12/12/2018")
        val formatter = DateTimeFormatter.ofPattern("d/MM/yyyy")
        val date = "12/12/2018"
        assertEquals(LocalDate.parse(date, formatter), localDate)
    }

    @Test
    fun toStringDateTest(){
        val localDate = DateUtils.asLocalDate("12/12/2018")
        val date = DateUtils.asString(localDate)
        assertEquals("2018-12-12", date)
    }
}