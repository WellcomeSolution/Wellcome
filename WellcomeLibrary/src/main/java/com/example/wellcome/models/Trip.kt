package com.example.wellcome.models

import java.util.*

class Trip(slotDate: SlotDate, val tripCity : TripCity, cost: Double,
           val hostConfiguration: HostConfiguration, val phone:String,val adress:String, val travelers: Int, val hasDogs : Boolean, val hasBabies:Boolean, val hasChilds:Boolean,)
    : Offer(slotDate) {

}