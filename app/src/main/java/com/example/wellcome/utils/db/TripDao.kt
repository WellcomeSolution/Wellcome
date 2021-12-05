package com.example.wellcome.utils.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TripDao {
    @Insert
    fun insert(vararg trips: Trip)
    @Query("SELECT * FROM trip")
    fun getAll(): List<Trip>
    @Query("SELECT * FROM trip WHERE id=:id ")
    fun findTripById(id: String): Trip
    @Query("SELECT * FROM trip WHERE adress like '%' || :email|| '%' ")
    fun findTripByTitle(email: String): List<Trip>
    @Query("SELECT * FROM trip WHERE hasBabies=:value ")
    fun findTripIncludeBaby(value : Boolean=true): List<Trip>
    @Query("SELECT * FROM trip WHERE hasDogs=:value ")
    fun findTripIncludeDog(value : Boolean=true): List<Trip>
    @Query("SELECT * FROM trip WHERE hasChilds=:value ")
    fun findTripIncludeChild(value : Boolean=true): List<Trip>
    @Query("UPDATE trip SET isAvailable=:isAvailable WHERE id = :id")
    fun update(isAvailable: Boolean?, id: Int)
    @Query("UPDATE trip SET like_trip= (like_trip+1) WHERE id = :id")
    fun updateLike(id: Int)
    @Query("UPDATE trip SET isFavorite=:value WHERE id  = :id")
    fun update(value:Boolean,id:Int)
    @Query("SELECT * FROM trip WHERE isFavorite=:value")
    fun findTripFavorites(value: Boolean=true): List<Trip>
}