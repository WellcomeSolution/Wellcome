package com.example.wellcome.db
import android.provider.BaseColumns

object Logement {
    object LogementEntry : BaseColumns {
        const val TABLE_NAME = "logement"
        const val COLUMN_NAME_TITRE_SERVICE = "titre"
        const val COLUMN_NAME_TELEPHONE = "telephone"
        const val COLUMN_NAME_ADDRESS = "address"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
        const val COLUMN_NAME_PERSONE = "nb_persone"
        const val COLUMN_NAME_PIECE = "nb_piece"
    }

    const val SQL_LOGEMENT_CREATE_ENTRIES = "CREATE TABLE " + LogementEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            LogementEntry.COLUMN_NAME_TITRE_SERVICE + " TEXT," +
            LogementEntry.COLUMN_NAME_TELEPHONE + " TEXT," +
            LogementEntry.COLUMN_NAME_ADDRESS + " TEXT," +
            LogementEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
            LogementEntry.COLUMN_NAME_TAGS + " TEXT," +
            LogementEntry.COLUMN_NAME_PERSONE + " TEXT," +
            LogementEntry.COLUMN_NAME_PIECE + " TEXT)"

    const val SQL_LOGEMENT_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + LogementEntry.TABLE_NAME
}