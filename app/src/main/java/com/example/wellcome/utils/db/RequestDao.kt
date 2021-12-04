package com.example.wellcome.utils.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RequestDao {
    @Insert
    fun insert(vararg request: TripRequest)
}