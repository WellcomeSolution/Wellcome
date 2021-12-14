package com.example.wellcome.db

import androidx.room.Entity

@Entity
class TripCity(val country : String,
               val city : String,
               val postalCode:String)