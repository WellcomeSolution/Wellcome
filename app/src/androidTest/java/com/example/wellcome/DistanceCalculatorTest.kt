package com.example.wellcome

import android.content.Context
import android.location.Geocoder
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.utils.DistanceCalculator
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class DistanceCalculatorTest {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var context: Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    @Test
    fun getDistanceBetweenTownTest()
    {
        val geo = Geocoder(context, Locale.getDefault())
        val res = geo.getFromLocationName("Paris",1)

        val distance = DistanceCalculator.Companion.getDistance(res.first().latitude,res.first().longitude,50.62925,3.057256)
        assertEquals(distance,204.0,10.0)
    }

    @Test
    fun getDistanceBetweenTownTestFalse()
    {
        val geo = Geocoder(context, Locale.getDefault())
        val res = geo.getFromLocationName("Paris",1)

        val distance = DistanceCalculator.Companion.getDistance(res.first().latitude,res.first().longitude,50.62925,3.057256)
        assertNotEquals(distance,100.0,10.0)
    }
}