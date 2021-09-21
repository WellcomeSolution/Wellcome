package com.example.wellcome

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.wellcome.models.Hosting
import com.example.wellcome.utils.HostingContract
import com.example.wellcome.utils.WellcomeDbContext
import com.example.wellcome.utils.WellcomeDbHelper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

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

    @Test
    fun createHostingTest(){
        val hosting = Hosting(
            "JEBBARI",
            "Rayhane",
            "9 rue du puits grenet Ermont 95120",
            "jebbarirayhane@gmail.com",
            "0668319800"
        )
        val id = dbContext.insertHosting(hosting)
        Assert.assertNotNull(id)
    }

    @Test
    fun getHostings(){
        seedDatabase()
        val hostings = dbContext.getHostings()

        Assert.assertEquals(2, hostings.count())
    }

     fun seedDatabase(){
         val hosting1 = Hosting(
             "JEBBARI",
             "Rayhane",
             "9 rue du puits grenet Ermont 95120",
             "jebbarirayhane@gmail.com",
             "0668319800"
         )
         val hosting2 = Hosting(
             "JEBBARI2",
             "Rayhane",
             "9 rue du puits grenet Ermont 95120",
             "jebbarirayhane@gmail.com",
             "0668319800"
         )
         dbContext.insertHosting(hosting1)
         dbContext.insertHosting(hosting2)
     }
}