package com.example.wellcome.utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TripDao {
    @Insert
    fun insertAll(vararg users: Trip)
    @Query("SELECT * FROM trip")
    fun getAll(): List<Trip>
}