package com.example.wellcome

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.utils.CitiesHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JsonTest {
    private lateinit var context: Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun getCitiesTest(){
        val cities = CitiesHelper.getCities(context)
        Assert.assertNotNull(cities)
    }
}