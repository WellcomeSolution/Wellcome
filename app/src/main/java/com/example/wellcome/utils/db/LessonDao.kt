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
}