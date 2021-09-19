package com.example.wellcome.utils

import com.example.wellcome.utils.HostingContract.HostingEntry
import android.provider.BaseColumns

object HostingContract {
    object HostingEntry : BaseColumns {
        const val TABLE_NAME = "hosting"
        const val COLUMN_NAME_FIRST_NAME = "country"
        const val COLUMN_NAME_LAST_NAME = "city"
        const val COLUMN_NAME_ADDRESS = "street"
        const val COLUMN_NAME_EMAIL = "additionalAddress"
        const val COLUMN_NAME_PHONE = "hosterEmail"
    }

    const val SQL_CREATE_ENTRIES = "CREATE TABLE " + HostingEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            HostingEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
            HostingEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
            HostingEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            HostingEntry.COLUMN_NAME_EMAIL + " TEXT," +
            HostingEntry.COLUMN_NAME_PHONE + " TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HostingEntry.TABLE_NAME
}