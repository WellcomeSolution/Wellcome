package com.example.services
import java.util.*

class Trip(slotDate: SlotDate, val tripCity : TripCity, cost: Double,
           distance: Int, val hostConfiguration: HostConfiguration, val travelers: Int, val pets : Int, val babies:Int, val childs:Int)
    : Offer(slotDate) {

}