package com.example.services

import kotlinx.serialization.Serializable

@Serializable
class Host(val title: String,
           val description: String,
           val address:Address,
           val contact: Contact,
           val configuration: HostConfiguration,
           val travelers: TravelersConfiguration)

