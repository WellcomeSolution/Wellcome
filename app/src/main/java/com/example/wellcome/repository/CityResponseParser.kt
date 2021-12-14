package com.example.wellcome.com.example.wellcome.repository

import com.example.wellcome.repository.Address
import com.example.wellcome.repository.Cities
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import kotlin.streams.toList

class CityResponseParser {
    fun parse(input:InputStream) : ArrayList<Address> {
        val cities = Json { ignoreUnknownKeys = true }.decodeFromStream<ArrayList<Cities>>(input)
        return cities.stream().map { c -> c.address }
            .filter { a -> !a?.city.isNullOrBlank()  } .toList() as ArrayList<Address>
    }
}