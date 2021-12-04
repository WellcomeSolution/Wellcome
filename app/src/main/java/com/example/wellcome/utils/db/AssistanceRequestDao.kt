package com.example.wellcome.utils.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AssistanceRequestDao {
    @Insert
    fun insert(vararg request: AssistanceRequest)
}