package com.example.wellcome.utils.db

import androidx.room.TypeConverter
import com.example.wellcome.models.Equipments
import com.example.wellcome.models.Priority
import com.google.common.reflect.TypeToken
import utils.DateUtils
import java.time.LocalDate
import kotlin.collections.ArrayList
import java.lang.reflect.Type
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun toEquipments(value: String) = enumValueOf<Equipments>(value)

    @TypeConverter
    fun fromEquipments(value: Equipments) = value.name

    @TypeConverter
    fun toPriority(value: String) = enumValueOf<Priority>(value)

    @TypeConverter
    fun fromPriority(value: Priority) = value.name

    @TypeConverter
    fun storedStringToEquipment(value: String): List<Equipments> {
        val dbValues: List<String> = value.split(",").filter { !it.isEmpty()}
        val enums: MutableList<Equipments> = ArrayList()
        for (s in dbValues) enums.add(Equipments.valueOf(s))
        return enums
    }

    @TypeConverter
    fun equipmentToStoredString(e: List<Equipments>): String {
        var value = ""
        for (equipment in e) value += equipment.name + ","
        return value
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toDate(dateString: String): LocalDate = DateUtils.asLocalDate(dateString)

    @TypeConverter
    fun fromDate(date: LocalDate): String = DateUtils.asString(date)
}