package com.example.wellcome

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.models.Equipments
import utils.DateUtils
import com.example.wellcome.utils.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class SQLiteTest {
    private lateinit var dbContext: WellcomeDbContext
    private lateinit var context:Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        dbContext = WellcomeDbContext(context)
    }

    @After
    fun clean(){
        context.deleteDatabase(WellcomeDbHelper.DATABASE_NAME)
    }

   /* @Test
    fun createLogementTest(){
        val host = Host(
            "title",
            "description",
            "9 rue du puits grenet Ermont 95120",
            "0668319800",
            listOf("baby", "handicape"),
            "3",
            "2"
        )
        val id = dbContext.insertLogement(host)
        Assert.assertNotNull(id)
    }

    @Test
    fun serviceContainsTagTest(){
        var assistance = Assistance("title", "description", "address", "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagExist("babe")
        assertTrue(result)
    }

    @Test
    fun createAssistanceTest(){
        val assistance = Assistance(
            "title",
            "description",
            "9 rue du puits grenet Ermont 95120",
            "0668319800",
            listOf("baby", "handicape"),
            "urgent"
        )
        val id = dbContext.insertAssistance(assistance)
        Assert.assertNotNull(id)
    }*/

    @Test
    fun test(){
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).build()

        db.clearAllTables()

        val trip : Trip =
            Trip(getSlotDate(), getTripCity(), getHostConfiguration(),
                2, false, false, false)

        db.tripDao().insertAll(trip)
        val t = db.tripDao().getAll()

    }

    private fun getSlotDate() : SlotDate
            = SlotDate(DateUtils.asLocalDate("12/12/2018"), DateUtils.asLocalDate("15/12/2018"))

    private fun getHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, listOf(Equipments.Wifi))

    private fun getTripCity() : TripCity
            = TripCity("France", "Soisy", "95230")
}