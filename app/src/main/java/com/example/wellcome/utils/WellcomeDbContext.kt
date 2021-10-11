package com.example.wellcome.utils

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

class WellcomeDbContext(context:Context) {
    val dpHelper = WellcomeDbHelper(context)

    fun insertHosting(hosting: com.example.wellcome.models.Hosting): Long? {
        val db = dpHelper.writableDatabase

        val values = ContentValues()
            values.put(HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME, hosting.firstName)
            values.put(HostingContract.HostingEntry.COLUMN_NAME_LAST_NAME, hosting.lastName)
            values.put(HostingContract.HostingEntry.COLUMN_NAME_ADDRESS, hosting.address)
            values.put(HostingContract.HostingEntry.COLUMN_NAME_EMAIL, hosting.email)
            values.put(HostingContract.HostingEntry.COLUMN_NAME_PHONE, hosting.phone)

        return db?.insert(HostingContract.HostingEntry.TABLE_NAME, null, values)
    }
    fun insertAssistance(assistance: com.example.wellcome.models.Assistance): Long? {
        val db = dpHelper.writableDatabase
        var tags_string:String = assistance.tags.joinToString()
        val values = ContentValues()
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE, assistance.title)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_DESCRIPTION, assistance.description)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_ADDRESS, assistance.address)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TELEPHONE, assistance.phone)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_PRIORITY, assistance.priority)

        return db?.insert(Assistance.AssistanceEntry.TABLE_NAME, null, values)
    }
    fun insertCours(cours: com.example.wellcome.models.Lesson): Long? {
        val db = dpHelper.writableDatabase
        var tags_string:String = cours.tags.joinToString()
        val values = ContentValues()
        values.put(Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE, cours.title)
        values.put(Cours.CoursEntry.COLUMN_NAME_DESCRIPTION, cours.description)
        values.put(Cours.CoursEntry.COLUMN_NAME_ADDRESS, cours.address)
        values.put(Cours.CoursEntry.COLUMN_NAME_TELEPHONE, cours.phone)
        values.put(Cours.CoursEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Cours.CoursEntry.COLUMN_NAME_DURATION, cours.sessionDuration)

        return db?.insert(Cours.CoursEntry.TABLE_NAME, null, values)
    }
    fun insertLogement(logement: com.example.wellcome.models.Host): Long? {
        val db = dpHelper.writableDatabase
        var tags_string:String = logement.tags.joinToString()
        val values = ContentValues()
        values.put(Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE, logement.title)
        values.put(Logement.LogementEntry.COLUMN_NAME_DESCRIPTION, logement.description)
        values.put(Logement.LogementEntry.COLUMN_NAME_ADDRESS, logement.address)
        values.put(Logement.LogementEntry.COLUMN_NAME_TELEPHONE, logement.phone)
        values.put(Logement.LogementEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Logement.LogementEntry.COLUMN_NAME_PERSONE, logement.nombrePersonne)
        values.put(Logement.LogementEntry.COLUMN_NAME_PIECE, logement.nombrePiece)


        return db?.insert(Logement.LogementEntry.TABLE_NAME, null, values)
    }
    fun getHostings(): List<com.example.wellcome.models.Hosting> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME} DESC"

        val cursor = db.query(
                HostingContract.HostingEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        )

        val hostings =  arrayListOf<com.example.wellcome.models.Hosting>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val firstName = getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME))
                val lastName = getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_LAST_NAME))
                val address = getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_ADDRESS))
                val email = getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_EMAIL))
                val phone = getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_PHONE))

                val hosting = com.example.wellcome.models.Hosting(
                    id,
                    firstName,
                    lastName,
                    address,
                    email,
                    phone
                )
                hostings.add(hosting)
            }
        }
        cursor.close()
        return hostings
    }
}