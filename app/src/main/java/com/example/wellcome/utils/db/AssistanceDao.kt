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
    @Query("SELECT * FROM assistance WHERE title like '%' || :title || '%' ")
    fun findAssistanceByTitle(title: String): List<Assistance>
    @Query("SELECT * FROM assistance WHERE tags IN (:tags)")
    fun findAssistanceByTags(tags: List<String>): List<Assistance>
    @Query("UPDATE assistance SET like_assistance= (like_assistance+1) WHERE id = :id")
    fun updateLike(id: Int)
    @Query("UPDATE assistance SET isFavorite=:value WHERE id  = :id")
     fun update(value:Boolean,id:Int)
    @Query("SELECT * FROM assistance WHERE isFavorite=:value")
    fun findAssistanceFavorites(value: Boolean=true): List<Assistance>

    @Query("SELECT * FROM assistance WHERE tags like '%' || :tag || '%'")
    fun findAssistanceByOneTag(tag: String): List<Assistance>
}