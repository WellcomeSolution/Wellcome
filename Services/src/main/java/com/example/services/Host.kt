package com.example.services

class Host(title: String, description: String, address:Address, phone: String,
           tags: List<String>, val slotsDate : List<SlotDate>, private val hostTravelers: HostTravelers,
           private val hostConfiguration: HostConfiguration): Service(title, description,
    address,phone, tags), IHost {

    override fun isHostRestrictionValid(trip: Trip) : Boolean {
        return when {
            isTravelersValid(trip.travelers) && isBabiesValid(trip.babies)
                    && isPetsValid(trip.pets) && isChildsValid(trip.childs) -> true
            else -> false
        }
        return true
    }

    private fun isBabiesValid(babies : Int) : Boolean = hostTravelers.babies >= babies

    private fun isPetsValid(pets: Int) : Boolean = hostTravelers.pets >= pets

    private fun isChildsValid(childs: Int) : Boolean = hostTravelers.childs >= childs

    private fun isTravelersValid(travelers : Int) : Boolean = hostTravelers.travelers >= travelers

    override fun isHostConfigurationValid(hostConfiguration: HostConfiguration): Boolean {
        return when {
            isRoomsValid(hostConfiguration.rooms) && isBedsValid(hostConfiguration.beds)
                    && isBathroomsValid(hostConfiguration.bathrooms) -> true
            else -> false
        }
    }

    private fun isRoomsValid(rooms : Int) : Boolean = hostConfiguration.rooms >= rooms

    private fun isBedsValid(beds : Int) : Boolean = hostConfiguration.beds >= beds

    private fun isBathroomsValid(bathrooms : Int) : Boolean = hostConfiguration.bathrooms >= bathrooms
}

