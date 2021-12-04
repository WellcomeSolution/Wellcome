package com.example.wellcome.utils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Trip::class, Lesson::class, Assistance::class, TripRequest::class, LessonRequest::class, AssistanceRequest::class], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun assistanceDao(): AssistanceDao
    abstract fun lessonDao(): LessonDao
    abstract fun requestTripDao(): RequestDao
    abstract fun requestLessonDao() : LessonRequestDao
    abstract fun requestAssistanceDao() : AssistanceRequestDao
}