package com.example.wellcome.repository

import com.example.services.AccountDto
import com.example.services.HostDetails
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class AccountResponseParser {
    private val format = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    fun parseToResponseMessage(input: InputStream) : String
            = format.decodeFromStream(input)
    fun parseToAccount(input: InputStream) : AccountDto
            = format.decodeFromStream(input)
}