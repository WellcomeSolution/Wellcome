package com.example.wellcome.models

class Host(title: String, description: String, address:Address, phone: String,
           tags: List<String>, val slotsDate : List<SlotDate>, private val hostRestriction: HostRestrictions,
           private val hostConfiguration: HostConfiguration): Service(title, description,
    address,phone, tags), IHost {

    override fun isHostRestrictionValid(trip: Trip) : Boolean{
        return when {
            isTravelersValid(trip.travelers) && isBabiesValid(trip.hasBabies)
                    && isDogsValid(trip.hasDogs) && isChildsValid(trip.hasChilds) -> true
            else -> false
        }
    }

    private fun isBabiesValid(hasBabies : Boolean) : Boolean {
        if(!hostRestriction.isBabiesAllowed && hasBabies)
            return false;
        return true;
    }

    private fun isDogsValid(hasDogs: Boolean) : Boolean {
        if(!hostRestriction.isDogsAllowed && hasDogs)
            return false;
        return true;
    }

    private fun isChildsValid(hasChilds: Boolean) : Boolean {
        if(!hostRestriction.isChildsAllowed && hasChilds)
            return false;
        return true;
    }

    private fun isTravelersValid(expectedTravelers : Int) : Boolean = hostRestriction.travelers >= expectedTravelers

    override fun isHostConfigurationValid(expectedHostConfiguration: HostConfiguration): Boolean {
        return when {
            isRoomsValid(expectedHostConfiguration.rooms) && isBedsValid(expectedHostConfiguration.beds)
                    && isBathroomsValid(expectedHostConfiguration.bathrooms) -> true
            else -> false
        }
    }

    private fun isRoomsValid(expectedRooms : Int) : Boolean = hostConfiguration.rooms >= expectedRooms

    private fun isBedsValid(expectedBeds : Int) : Boolean = hostConfiguration.beds >= expectedBeds

    private fun isBathroomsValid(expectedBathrooms : Int) : Boolean = hostConfiguration.bathrooms >= expectedBathrooms
}

