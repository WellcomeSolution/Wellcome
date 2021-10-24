package com.example.wellcome.models

class TripFinder {
    fun isHostMatching(trip : Trip, host: Host) : Boolean {
        return when{
            isSlotDateMatching(trip.slotDate, host.slotsDate) && isCityMatching(trip.tripCity, host.address)
                    && isHostConfigurationMatching(trip.hostConfiguration, host) && isHostRestrictionMatching(trip, host) -> true
            else -> false
        }
    }

    private fun isCityMatching(city: TripCity, address: Address) : Boolean {
        fun checkCountry() : Boolean = city.country.equals(address.country.addressLine, ignoreCase = true)
        fun checkPostalCode() : Boolean = city.postalCode.equals(address.country.administrativeArea.locality.postalCode.addressLine, ignoreCase = true)
        fun checkCity() : Boolean = city.city.equals(address.country.administrativeArea.locality.addressLine, ignoreCase = true)

        return when {
            checkCountry() && checkPostalCode() && checkCity() -> true;
            else -> false;
        }
    }

    private fun isSlotDateMatching(slotDate: SlotDate, slotsDate: List<SlotDate>) = slotsDate.contains(slotDate)

    private fun isHostConfigurationMatching(expectedConfiguration: HostConfiguration, host: Host) : Boolean
        = host.isHostConfigurationValid(expectedConfiguration)

    private fun isHostRestrictionMatching(trip: Trip, host: Host)
        = host.isHostRestrictionValid(trip)
}