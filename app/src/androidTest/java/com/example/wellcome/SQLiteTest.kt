package com.example.wellcome

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.models.Equipments
import com.example.wellcome.models.Priority
import com.example.wellcome.utils.db.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.DateUtils

class SQLiteTest {
    private lateinit var db : AppDatabase
    private lateinit var context:Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().build()
    }

    @After
    fun clean(){
        db.clearAllTables()
    }

    @Test
    fun lessonInsertTest(){
        val lesson = Lesson(
            false,
            "title",
            "description",
            getAddress(),
            "0668319800",
            false,
            listOf("baby", "handicape"),
            3
        )

        db.lessonDao().insert(lesson)
        val lessons = db.lessonDao().getAll()
        Assert.assertEquals(1, lessons.size)
    }

    @Test
    fun assistanceInsertTest(){
        val assistance = Assistance(
            isFavorite = false,
            "title",
            "description",
            getAddress(),
            "0668319800",
            listOf("baby", "handicape"),
            true,
            Priority.High
        )

        db.assistanceDao().insert(assistance)
        val assistances = db.assistanceDao().getAll()
        Assert.assertEquals(1, assistances.size)
    }

    private fun getAddress() : Address
            = Address(
        country = Country(
        addressLine = "France",
        administrativeArea = AdministrativeArea(
            addressLine = "Val d'oise", locality = Locality(
                addressLine = "Soisy",
                thoroughfare = Thoroughfare(addressLine = "9 rue du puits grenet"),
                postalCode = PostalCode(addressLine = "95230")
            )
        )
    )
    )
    @Test
    fun tripInsertTest(){
        /*val trip : Trip =
            Trip(getSlotDate(), getTripCity(), getHostConfiguration(),
                66831, "test", 0, false)

        db.tripDao().insert(trip)
        val trips = db.tripDao().getAll()
        Assert.assertEquals(1, trips.size)*/
    }

    private fun getSlotDate() : SlotDate
            = SlotDate(DateUtils.asLocalDate("12/12/2018"), DateUtils.asLocalDate("15/12/2018"))

    private fun getHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, listOf(Equipments.Wifi))

    private fun getTripCity() : TripCity
            = TripCity("France", "Soisy", "95230")
}