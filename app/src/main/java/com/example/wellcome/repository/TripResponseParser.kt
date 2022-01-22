package com.example.wellcome.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.services.FileUploadResult
import com.example.services.HostDetails
import com.example.services.HostPresenter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class TripResponseParser {
    fun parseToHostDetails(input:InputStream) : HostDetails
        = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    fun parseToHostPresenters(input:InputStream) : ArrayList<HostPresenter>
        = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    fun parseToHostPresenter(input:InputStream) : HostPresenter
            = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    fun parseToFileUploadResult(input:InputStream) : FileUploadResult
            = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
}