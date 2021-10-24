package com.example.wellcome.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.wellcome.utils.Assistance.SQL_ASSISTANCE_CREATE_ENTRIES
import com.example.wellcome.utils.Assistance.SQL_ASSISTANCE_DELETE_ENTRIES
import com.example.wellcome.utils.Lesson.SQL_LESSON_CREATE_ENTRIES
import com.example.wellcome.utils.Lesson.SQL_LESSON_DELETE_ENTRIES
import com.example.wellcome.utils.Logement.SQL_LOGEMENT_CREATE_ENTRIES
import com.example.wellcome.utils.Logement.SQL_LOGEMENT_DELETE_ENTRIES

class WellcomeDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_ASSISTANCE_CREATE_ENTRIES)
        db.execSQL(SQL_LESSON_CREATE_ENTRIES)
        db.execSQL(SQL_LOGEMENT_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_ASSISTANCE_DELETE_ENTRIES)
        db.execSQL(SQL_LESSON_DELETE_ENTRIES)
        db.execSQL(SQL_LOGEMENT_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = "Wellcome.db"
    }
}