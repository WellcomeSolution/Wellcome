package com.example.wellcome.utils.db

import android.provider.BaseColumns
import androidx.room.Entity

@Entity
class TripCity(val country : String,
               val city : String,
               val postalCode:String,
               val address: String)