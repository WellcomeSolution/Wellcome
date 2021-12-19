package com.example.wellcome.repository

import com.example.services.Host
import com.example.wellcome.repository.Address
import com.example.wellcome.repository.TripRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import kotlin.streams.toList

class TripResponseParser {
    fun parse(input:InputStream) : ArrayList<Host>
    {
        return Json { ignoreUnknownKeys = true }.decodeFromStream<ArrayList<Host>>(input)
    }

}