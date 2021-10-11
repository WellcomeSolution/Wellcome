package com.example.wellcome.utils
import android.provider.BaseColumns

object Cours {
    object CoursEntry : BaseColumns {
        const val TABLE_NAME = "cours"
        const val COLUMN_NAME_TITRE_SERVICE = "titre"
        const val COLUMN_NAME_TELEPHONE = "telephone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
        const val COLUMN_NAME_DURATION = "duration"
    }

    const val SQL_COURS_CREATE_ENTRIES = "CREATE TABLE " + CoursEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            CoursEntry.COLUMN_NAME_TITRE_SERVICE + " TEXT," +
            CoursEntry.COLUMN_NAME_TELEPHONE + " TEXT," +
            CoursEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            CoursEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
            CoursEntry.COLUMN_NAME_TAGS + " TEXT," +
            CoursEntry.COLUMN_NAME_DURATION  + " TEXT)"

    const val SQL_COURS_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CoursEntry.TABLE_NAME
}