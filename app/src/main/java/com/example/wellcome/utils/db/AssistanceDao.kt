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
    @Query("SELECT * FROM assistance WHERE id=:id ")
    fun findAssistanceById(id: String): Assistance
    @Query("SELECT * FROM assistance WHERE title=:title ")
    fun findAssistanceByTitle(title: String): List<Assistance>
    @Query("SELECT * FROM assistance WHERE tags IN (:tags)")
    fun findAssistanceByTags(tags: List<String>): List<Assistance>
}