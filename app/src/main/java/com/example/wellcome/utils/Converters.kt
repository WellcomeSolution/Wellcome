package com.example.wellcome.utils

import androidx.room.TypeConverter
import com.example.wellcome.models.Equipments
import utils.DateUtils
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Converters {
    @TypeConverter
    fun toEquipments(value: String) = enumValueOf<Equipments>(value)

    @TypeConverter
    fun fromEquipments(value: Equipments) = value.name

    @TypeConverter
    fun storedStringToEquipment(value: String): List<Equipments> {
        val dbValues: List<String> = value.split(",").filterNotNull()
        val enums: MutableList<Equipments> = ArrayList()
        for (s in dbValues) enums.add(Equipments.valueOf(s.trim(',')))
        return enums
    }

    @TypeConverter
    fun equipmentToStoredString(e: List<Equipments>): String {
        var value = ""
        for (equipment in e) value += equipment.name + ","
        return value
    }

    @TypeConverter
    fun toDate(dateString: String): LocalDate = DateUtils.Companion.asLocalDate(dateString)

    @TypeConverter
    fun fromDate(date: LocalDate): String = DateUtils.Companion.asString(date)
}