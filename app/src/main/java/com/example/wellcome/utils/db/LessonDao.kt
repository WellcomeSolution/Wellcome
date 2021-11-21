package com.example.wellcome.utils.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LessonDao {
    @Insert
    fun insert(vararg lessons: Lesson)
    @Query("SELECT * FROM lesson")
    fun getAll(): List<Lesson>
    @Query("SELECT * FROM lesson WHERE id=:id ")
    fun findLessonById(id: String): Lesson
    @Query("SELECT * FROM lesson WHERE title=:title ")
    fun findLessonByTitle(title: String): List<Lesson>
    @Query("SELECT * FROM lesson WHERE tags IN (:tags)")
    fun findLessonByTags(tags: List<String>): List<Lesson>
    @Query("UPDATE lesson SET isAvailable=:isAvailable WHERE id = :id")
    fun update(isAvailable: Boolean?, id: Int)
    @Query("UPDATE assistance SET isFavorite=:value WHERE id  = :id")
    fun update(value:Boolean,id:Int)

    @Query("SELECT * FROM assistance WHERE isFavorite=:value")
    fun findAssistanceFavorites(value: Boolean=true): List<Lesson>
}