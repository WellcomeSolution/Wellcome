package com.example.services
import utils.DateUtils
import org.junit.Test
import org.junit.Assert.*
import kotlin.collections.ArrayList

class TripFinderTests {
    private val tripFinder = TripFinder()
    private val mockup = Mockup()

    @Test
    fun findTripsTest(){
        val host = mockup.getHosts().first()
        val trip =
            Trip(getTripSlotDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 2, 0, 1, 0)

        assertTrue(tripFinder.isHostMatching(trip, host))
    }

    @Test
    fun findTripsInvalidTravelersTest(){
        val hostDetails : HostDetails
                = HostDetails("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 3, 0, 1, 0)

        assertFalse(tripFinder.isHostMatching(trip, hostDetails))
    }

    @Test
    fun findTripsInvalidDogsTest(){
        val hostDetails : HostDetails
                = HostDetails("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 2, 1, 1, 0)

        assertFalse(tripFinder.isHostMatching(trip, hostDetails))
    }

    @Test
    fun findTripsInvalidCityTest(){
        val hostDetails : HostDetails
                = HostDetails("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotDate(), TripCity("Japan", "Tokyo", "163-8001"), 1.0, -1,
                getTripHostConfiguration(), 2, 0, 1, 0)

        assertFalse(tripFinder.isHostMatching(trip, hostDetails))
    }

    @Test
    fun findTripsInvalidChildsTest(){
        val hostDetails : HostDetails
                = HostDetails("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 2, 0, 1, 1)

        assertFalse(tripFinder.isHostMatching(trip, hostDetails))
    }

    @Test
    fun findTripsValidBabiesTest(){
        val hostDetails : HostDetails
                = HostDetails("MyTitle","MyDescription", getHostAddress(), "0668319800", ArrayList(),
            getHostSlotsDate(), getHostRestriction(), getHostConfiguration())
        val trip : Trip =
            Trip(getTripSlotDate(), TripCity("France", "Soisy", "95230"), 1.0, -1,
                getTripHostConfiguration(), 2, 0, 0, 0)

        assertTrue(tripFinder.isHostMatching(trip, hostDetails))
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
        = listOf(SlotDate(DateUtils.asLocalDate("12/12/2018" ), DateUtils.asLocalDate("15/12/2018")),
            SlotDate(DateUtils.asLocalDate("16/12/2018" ), DateUtils.asLocalDate("18/12/2018"))
            )

    private fun getTripSlotDate() : SlotDate
            = SlotDate(DateUtils.asLocalDate("12/12/2018" ), DateUtils.asLocalDate("15/12/2018"))

    private fun getHostRestriction() : HostTravelers
        = HostTravelers(2, 1, 0, 0)

    private fun getHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, ArrayList())

    private fun getTripHostConfiguration() : HostConfiguration
            = HostConfiguration(2, 1, 1, ArrayList())
}