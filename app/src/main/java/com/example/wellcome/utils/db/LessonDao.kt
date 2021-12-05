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
    @Query("SELECT * FROM lesson WHERE title like '%' || :title || '%' ")
    fun findLessonByTitle(title: String): List<Lesson>
    @Query("SELECT * FROM lesson WHERE tags IN (:tags)")
    fun findLessonByTags(tags: List<String>): List<Lesson>
    @Query("UPDATE lesson SET isAvailable=:isAvailable WHERE id = :id")
    fun update(isAvailable: Boolean?, id: Int)
    @Query("UPDATE lesson SET like_lesson= (like_lesson+1) WHERE id = :id")
    fun updateLike(id: Int)
    @Query("UPDATE lesson SET isFavorite=:value WHERE id  = :id")
    fun update(value:Boolean,id:Int)
    @Query("SELECT * FROM lesson WHERE isFavorite=:value")
    fun findLessonFavorites(value: Boolean=true): List<Lesson>
    @Query("SELECT * FROM lesson WHERE tags like '%' || :tag || '%'")
    fun findLessonByOneTag(tag: String): List<Lesson>
}