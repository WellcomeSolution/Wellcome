package com.example.wellcome.utils.db

import android.provider.BaseColumns

object Host {
    object HostEntry : BaseColumns {
        const val TABLE_NAME = "host"
        const val COLUMN_NAME_TITLE_SERVICE = "title"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
    }

    const val SQL_HOST_CREATE_ENTRIES = "CREATE TABLE " + HostEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            HostEntry.COLUMN_NAME_TITLE_SERVICE + " TEXT," +
            HostEntry.COLUMN_NAME_PHONE + " TEXT," +
            HostEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            HostEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
            HostEntry.COLUMN_NAME_TAGS + " TEXT)"

    const val SQL_HOST_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HostEntry.TABLE_NAME
}