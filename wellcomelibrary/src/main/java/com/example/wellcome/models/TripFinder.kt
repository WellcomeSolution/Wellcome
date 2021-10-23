package com.example.wellcome.models

class TripFinder {
    fun isHostMatching(trip : Trip, host: Host) : Boolean {
        return when{
            checkSlotDate(trip.slotDate, host.slotsDate) && checkCity(trip.tripCity, host.address)
                    && checkHostConfiguration(trip.hostConfiguration, host) && checkHostRestriction(trip, host) -> true
            else -> false
        }
    }

    private fun checkCity(city: TripCity, address: Address) : Boolean {
        fun checkCountry() : Boolean = city.country.equals(address.country.addressLine, ignoreCase = true)
        fun checkPostalCode() : Boolean = city.country.equals(address.country.administrativeArea.locality.postalCode.addressLine, ignoreCase = true)
        fun checkCity() : Boolean = city.country.equals(address.country.administrativeArea.locality.addressLine, ignoreCase = true)

        return when {
            checkCountry() && checkPostalCode() && checkCity() -> true;
            else -> false;
        }
    }

    private fun checkSlotDate(slotDate: SlotDate, slotsDate: List<SlotDate>) = slotsDate.contains(slotDate)

    private fun checkHostConfiguration(expectedConfiguration: HostConfiguration, host: Host) : Boolean
        = host.isHostConfigurationValid(expectedConfiguration)

    private fun checkHostRestriction(trip: Trip, host: Host)
        = host.isHostRestrictionValid(trip)
}