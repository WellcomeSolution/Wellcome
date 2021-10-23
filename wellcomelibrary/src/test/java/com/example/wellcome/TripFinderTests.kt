package com.example.wellcome

import com.example.wellcome.models.*
import com.example.wellcome.models.utils.DateUtils
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import kotlin.collections.ArrayList

class TripFinderTests {
    val tripFinder = TripFinder()

    @Test
    fun findTrips(){
        val host : Host
            = Host("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotsDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 2, false, true, false)

        assertTrue(tripFinder.isHostMatching(trip, host))
    }

    private fun getHostAddress() : Address
        = Address(country = Country(
            addressLine = "France",
            administrativeArea = AdministrativeArea(
                addressLine = "Val d'oise", locality = Locality(
                    addressLine = "Soisy",
                    thoroughfare = Thoroughfare(addressLine = "9 rue du puits grenet"),
                    postalCode = PostalCode(addressLine = "95230")
                )
            )
        ))

    private fun getHostSlotsDate() : List<SlotDate>
        = listOf(SlotDate(DateUtils.asDate("2018-12-12" ), DateUtils.asDate("2018-12-15")),
            SlotDate(DateUtils.asDate("2018-12-16" ), DateUtils.asDate("2018-12-18"))
            )

    private fun getTripSlotsDate() : SlotDate
            = SlotDate(DateUtils.asDate("2018-12-12" ), DateUtils.asDate("2018-12-15"))
    )

    private fun getHostRestriction() : HostRestrictions
        = HostRestrictions(2, true, false, false)

    private fun getHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, ArrayList())

    private fun getTripHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, ArrayList())
}