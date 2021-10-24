package com.example.wellcome.utils
import android.provider.BaseColumns

object Assistance {
    object AssistanceEntry : BaseColumns {
        const val TABLE_NAME = "assistance"
        const val COLUMN_NAME_TITLE_SERVICE = "title"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
        const val COLUMN_NAME_PRIORITY = "priority"
    }

    const val SQL_ASSISTANCE_CREATE_ENTRIES = "CREATE TABLE " + AssistanceEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            AssistanceEntry.COLUMN_NAME_TITLE_SERVICE + " TEXT," +
            AssistanceEntry.COLUMN_NAME_PHONE + " TEXT," +
            AssistanceEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            AssistanceEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
            AssistanceEntry.COLUMN_NAME_TAGS + " TEXT," +
            AssistanceEntry.COLUMN_NAME_PRIORITY + " TEXT)"

    const val SQL_ASSISTANCE_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + AssistanceEntry.TABLE_NAME
}