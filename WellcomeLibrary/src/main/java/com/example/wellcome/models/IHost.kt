package com.example.wellcome.models

interface IHost {
    fun isHostConfigurationValid(hostConfiguration: HostConfiguration) : Boolean
    fun isHostRestrictionValid(trip: Trip) : Boolean
}