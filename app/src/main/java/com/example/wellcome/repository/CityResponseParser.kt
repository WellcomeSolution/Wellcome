package com.example.wellcome

import com.example.wellcome.repository.Address
import com.example.wellcome.repository.City
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import kotlin.streams.toList

class CityResponseParser {
    fun parse(input:InputStream) : ArrayList<City> {
        return Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    }
}