package com.example.wellcome.utils.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AssistanceDao {
    @Insert
    fun insert(vararg assistances: Assistance)
    @Query("SELECT * FROM assistance")
    fun getAll(): List<Assistance>
}