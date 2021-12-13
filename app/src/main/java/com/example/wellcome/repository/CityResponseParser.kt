package com.example.wellcome.com.example.wellcome.repository

import com.example.wellcome.repository.CityResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class CityResponseParser {
    fun parse(input:InputStream) : CityResponse {
        return Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    }
}