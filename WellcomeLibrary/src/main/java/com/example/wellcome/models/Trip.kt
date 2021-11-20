package com.example.wellcome.models

import java.util.*

class Trip(slotDate: SlotDate, val tripCity : TripCity, cost: Double,
           distance: Int, val hostConfiguration: HostConfiguration, val travelers: Int, val hasDogs : Boolean, val hasBabies:Boolean, val hasChilds:Boolean,)
    : Offer(slotDate) {

}