package com.example.wellcome.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.services.Host
import com.example.services.HostPresenter
import com.example.wellcome.repository.Address
import com.example.wellcome.repository.TripRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import kotlin.streams.toList

class TripResponseParser {
    fun parseToHosts(input:InputStream) : ArrayList<Host>
        = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    fun parseToHostPresenters(input:InputStream) : ArrayList<HostPresenter>
        = Json { ignoreUnknownKeys = true }.decodeFromStream(input)
    fun parseToBitmap(input:InputStream) : Bitmap
        = BitmapFactory.decodeStream(input)
}