package com.example.services

interface IHost {
    fun isHostConfigurationValid(hostConfiguration: HostConfiguration) : Boolean
    fun isHostRestrictionValid(trip: Trip) : Boolean
}