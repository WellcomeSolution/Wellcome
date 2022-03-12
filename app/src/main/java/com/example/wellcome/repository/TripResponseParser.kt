package com.example.wellcome.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.services.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class TripResponseParser {
    private val format = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    fun parseToHostDetails(input:InputStream) : HostDetails
        = format.decodeFromStream(input)
    fun parseToHostPresenters(input:InputStream) : ArrayList<HostPresenter>
        = format.decodeFromStream(input)
    fun parseToHostPresenter(input:InputStream) : HostPresenter
        = format.decodeFromStream(input)
    fun parseToFileUploadResult(input:InputStream) : FileUploadResult
        = format.decodeFromStream(input)
    fun parseToHostRequest(input:InputStream) : HostReservationDto
            = format.decodeFromStream(input)
    fun parseToHostReservationPresenter(input:InputStream) : ArrayList<HostReservationPresenterDto>
            = format.decodeFromStream(input)
    fun parseToIncomingTrip(input:InputStream) : ArrayList<IncomingTripDto>
            = format.decodeFromStream(input)
}