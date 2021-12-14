package com.example.wellcome.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Trip::class, Lesson::class, Assistance::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun assistanceDao(): AssistanceDao
    abstract fun lessonDao(): LessonDao
}