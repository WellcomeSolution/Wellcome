package com.example.wellcome.utils

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.wellcome.models.Hosting
import java.util.*

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