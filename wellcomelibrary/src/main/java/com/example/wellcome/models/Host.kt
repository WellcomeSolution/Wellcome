package com.example.wellcome.models

import java.util.*

class Host(title: String, description: String, address:Address, phone: String,
           tags: List<String>, val slotsDate : List<SlotDate>, val hostRestriction: HostRestrictions, val hostConfiguration: HostConfiguration): Service(title, description,
    address,phone, tags), IHost {

    override fun isHostRestrictionValid(trip: Trip) : Boolean{
        return when {
            checkTravelers(trip.travelers) && checkBabies(trip.hasBabies)
                    && checkDogs(trip.hasDogs) && checkChilds(trip.hasChilds) -> true
            else -> false
        }
    }

    private fun checkBabies(hasBabies : Boolean) : Boolean = hostRestriction.IsBabiesAllowed == hasBabies

    private fun checkDogs(hasDogs: Boolean) : Boolean = hostRestriction.IsDogsAllowed == hasDogs

    private fun checkChilds(hasChilds: Boolean) : Boolean = hostRestriction.IsChildsAllowed == hasChilds

    private fun checkTravelers(expectedTravelers : Int) : Boolean = hostRestriction.travelers >= expectedTravelers

    override fun isHostConfigurationValid(expectedHostConfiguration: HostConfiguration): Boolean {
        return when {
            checkRooms(expectedHostConfiguration.rooms) && checkBeds(expectedHostConfiguration.beds)
                    && checkBathrooms(expectedHostConfiguration.bathrooms) -> true
            else -> false
        }
    }

    private fun checkRooms(expectedRooms : Int) : Boolean = hostConfiguration.rooms >= expectedRooms

    private fun checkBeds(expectedBeds : Int) : Boolean = hostConfiguration.beds >= expectedBeds

    private fun checkBathrooms(expectedBathrooms : Int) : Boolean = hostConfiguration.bathrooms >= expectedBathrooms
}

