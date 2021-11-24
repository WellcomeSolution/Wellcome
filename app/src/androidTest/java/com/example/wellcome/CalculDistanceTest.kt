package com.example.wellcome

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.utils.CalculDistance
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class CalculDistanceTest {
    private var longitude = 0.0
    private var latitude = 0.0

    @Test
    fun calculeDistanceTest() {
        val c = CalculDistance()
        val distance = c.distance(0.0, 0.0, 0.0, 0.0)
        assertEquals(distance, 0.0, 0.02)
    }

    @Test
    fun CalculDistanceBetweenTown()
    {
        val c = CalculDistance()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        getLastKnownLocation()

        val geo = Geocoder(context, Locale.getDefault())
        val res = geo.getFromLocationName("Paris",1)
        val lat = res.first().latitude
        val lont = res.first().longitude
        val distance = c.distance(lat,lont,50.62925,3.057256)
        assertEquals(distance,204.0,10.0)

    }

    @Test
    fun CalculDistanceBetweenTownFalse()
    {
        val c = CalculDistance()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        getLastKnownLocation()

        val geo = Geocoder(context, Locale.getDefault())
        val res = geo.getFromLocationName("Paris",1)
        val lat = res.first().latitude
        val lont = res.first().longitude
        val distance = c.distance(lat,lont,50.62925,3.057256)
        assertNotEquals(distance,100.0,10.0)

    }

    // declare a global variable of FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient

// in onCreate() initialize FusedLocationProviderClient


    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                }

            }

    }
}