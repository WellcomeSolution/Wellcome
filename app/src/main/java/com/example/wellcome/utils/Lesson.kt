package com.example.wellcome.utils
import android.provider.BaseColumns

object Lesson {
    object LessonEntry : BaseColumns {
        const val TABLE_NAME = "cours"
        const val COLUMN_NAME_TITLE_SERVICE = "title"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
        const val COLUMN_NAME_DURATION = "duration"
    }

    const val SQL_LESSON_CREATE_ENTRIES = "CREATE TABLE " + LessonEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            LessonEntry.COLUMN_NAME_TITLE_SERVICE + " TEXT," +
            LessonEntry.COLUMN_NAME_PHONE + " TEXT," +
            LessonEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            LessonEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
            LessonEntry.COLUMN_NAME_TAGS + " TEXT," +
            LessonEntry.COLUMN_NAME_DURATION  + " TEXT)"

    const val SQL_LESSON_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + LessonEntry.TABLE_NAME
}